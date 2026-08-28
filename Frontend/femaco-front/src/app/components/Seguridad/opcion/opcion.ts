import { CommonModule } from '@angular/common';
import { Component, inject, OnInit, signal } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Menu } from '../../../core/models/menu.model';
import { Opcion as OpcionModel } from '../../../core/models/opcion.model';
import { MenuNombrePipe } from '../../../core/pipes/menu-nombre-pipe';
import { UsuariosEmailPipe } from '../../../core/pipes/usuarios-email';
import { AuthService } from '../../../core/services/auth.service';
import { ConjuntoMenuService } from '../../../core/services/conjunto-menu.service';
import { MenuService } from '../../../core/services/menu.service';
import { OpcionService } from '../../../core/services/opcion.service';

@Component({
  selector: 'app-opcion',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, MenuNombrePipe, UsuariosEmailPipe],
  templateUrl: './opcion.html',
  styleUrl: './opcion.css',
})
export class Opcion implements OnInit {
  private fb = inject(FormBuilder);
  private opcionService = inject(OpcionService);
  private menuService = inject(MenuService);
  private authService = inject(AuthService);
  private conjuntoMenuService = inject(ConjuntoMenuService);

  form!: FormGroup;

  lista = signal<OpcionModel[]>([]);
  menus = signal<Menu[]>([]);
  cargando = signal(false);
  mensaje = signal('');
  error = signal('');
  editando = false;
  idEditando: number | null = null;

  opcionesPorMenu() {
    const grupos = this.menus()
      .map((menu) => ({
        menu,
        opciones: this.lista()
          .filter((opcion) => opcion.idMenu === menu.idMenu)
          .sort((a, b) => (a.ordenMenu ?? 999) - (b.ordenMenu ?? 999)),
      }))
      .filter((grupo) => grupo.opciones.length > 0);

    const sinMenu = this.lista()
      .filter((opcion) => !opcion.idMenu || !this.menus().some((menu) => menu.idMenu === opcion.idMenu))
      .sort((a, b) => (a.ordenMenu ?? 999) - (b.ordenMenu ?? 999));

    if (sinMenu.length > 0) {
      grupos.push({
        menu: { idMenu: 0, nombre: 'Sin menú asignado' } as Menu,
        opciones: sinMenu,
      });
    }

    return grupos;
  }

  get permisos() {
    return this.conjuntoMenuService.getPermisosPorPagina('opcion');
  }

  get puedeCrear(): boolean {
    return this.permisos.alta;
  }

  get puedeEditar(): boolean {
    return this.permisos.cambio;
  }

  get puedeEliminar(): boolean {
    return this.permisos.baja;
  }

  ngOnInit(): void {
    this.initForm();
    this.cargarLista();
    this.cargarMenus();
    this.menuService.loadNombreCache().subscribe();
  }

  private initForm(): void {
    this.form = this.fb.group({
      nombre: ['', [Validators.required, Validators.maxLength(100)]],
      ordenMenu: [1, [Validators.min(1)]],
      pagina: ['', [Validators.maxLength(150)]],
      idMenu: [null, [Validators.required, Validators.min(1)]],
    });
  }

  cargarLista(): void {
    this.cargando.set(true);
    this.error.set('');

    this.opcionService.buscarTodos().subscribe({
      next: (data) => {
        this.lista.set(data ?? []);
        this.cargando.set(false);
      },
      error: () => {
        this.error.set('Error al cargar las opciones');
        this.cargando.set(false);
      },
    });
  }

  cargarMenus(): void {
    this.menuService.buscarTodos().subscribe({
      next: (data) => {
        this.menus.set(data ?? []);
      },
      error: () => {
        this.error.set('Error al cargar los menús');
      },
    });
  }

  private finalizarOperacion(mensaje: string): void {
    this.mensaje.set(mensaje);
    this.resetForm();
    this.cargarLista();

    setTimeout(() => {
      this.mensaje.set('');
    }, 3500);
  }

  onSubmit(): void {
    if (this.editando && !this.puedeEditar) {
      this.error.set('No tienes permiso para modificar');
      return;
    }
    if (!this.editando && !this.puedeCrear) {
      this.error.set('No tienes permiso para crear');
      return;
    }

    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }

    const usuarioId = this.authService.getCurrentUser()?.idUsuario;
    if (!usuarioId) {
      this.error.set('No se encontró el usuario autenticado');
      return;
    }

    const datos: OpcionModel = {
      nombre: this.form.value.nombre,
      ordenMenu: this.form.value.ordenMenu ?? null,
      pagina: this.form.value.pagina?.trim() || undefined,
      idMenu: Number(this.form.value.idMenu),
      usuarioCreacion: usuarioId,
    };

    this.cargando.set(true);
    this.mensaje.set('');
    this.error.set('');

    if (this.editando && this.idEditando) {
      datos.usuarioModif = usuarioId;
      this.opcionService.actualizar(this.idEditando, datos).subscribe({
        next: () => this.finalizarOperacion('Opción actualizada correctamente'),
        error: () => {
          this.error.set('Error al actualizar la opción');
          this.cargando.set(false);
        },
      });
    } else {
      this.opcionService.crear(datos).subscribe({
        next: () => this.finalizarOperacion('Opción creada correctamente'),
        error: () => {
          this.error.set('Error al crear la opción');
          this.cargando.set(false);
        },
      });
    }
  }

  editar(item: OpcionModel): void {
    if (!this.puedeEditar) return;

    this.editando = true;
    this.idEditando = item.idOpcion!;
    this.form.patchValue({
      nombre: item.nombre,
      ordenMenu: item.ordenMenu ?? 1,
      pagina: item.pagina ?? '',
      idMenu: item.idMenu ?? null,
    });
    window.scrollTo({ top: 0, behavior: 'smooth' });
  }

  eliminar(id: number): void {
    if (!this.puedeEliminar) return;

    if (!confirm('¿Está seguro de eliminar esta opción?')) return;

    this.cargando.set(true);
    this.opcionService.eliminar(id).subscribe({
      next: () => this.finalizarOperacion('Opción eliminada correctamente'),
      error: () => {
        this.error.set('Error al eliminar la opción');
        this.cargando.set(false);
      },
    });
  }

  cancelar(): void {
    this.resetForm();
  }

  private resetForm(): void {
    this.form.reset({
      nombre: '',
      ordenMenu: 1,
      pagina: '',
      idMenu: null,
    });
    this.editando = false;
    this.idEditando = null;
    this.cargando.set(false);
  }

  get f() {
    return this.form.controls;
  }
}
