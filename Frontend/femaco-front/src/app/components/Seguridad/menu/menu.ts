import { CommonModule } from '@angular/common';
import { Component, inject, OnInit, signal } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Menu as MenuModel } from '../../../core/models/menu.model';
import { Modulo } from '../../../core/models/modulo.model';
import { ModulosNombrePipe } from '../../../core/pipes/modulos-nombre-pipe';
import { UsuariosEmailPipe } from '../../../core/pipes/usuarios-email';
import { AuthService } from '../../../core/services/auth.service';
import { ConjuntoMenuService } from '../../../core/services/conjunto-menu.service';
import { MenuService } from '../../../core/services/menu.service';
import { ModuloService } from '../../../core/services/modulo.service';
import { UsuarioService } from '../../../core/services/usuario.service';

@Component({
  selector: 'app-menu',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, UsuariosEmailPipe, ModulosNombrePipe],
  templateUrl: './menu.html',
  styleUrl: './menu.css',
})
export class Menu implements OnInit {
  private fb = inject(FormBuilder);
  private menuService = inject(MenuService);
  private moduloService = inject(ModuloService);
  private authService = inject(AuthService);
  private usuarioService = inject(UsuarioService);
  private conjuntoMenuService = inject(ConjuntoMenuService);

  form!: FormGroup;

  lista = signal<MenuModel[]>([]);
  modulos = signal<Modulo[]>([]);
  cargando = signal(false);
  mensaje = signal('');
  error = signal('');
  editando = false;
  idEditando: number | null = null;

  get permisos() {
    return this.conjuntoMenuService.getPermisosPorPagina('menu');
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
    this.cargarModulos();
    this.usuarioService.loadEmailCache().subscribe();
    this.moduloService.loadNombreCache().subscribe();
  }

  private initForm(): void {
    this.form = this.fb.group({
      nombre: ['', [Validators.required, Validators.maxLength(100)]],
      ordenMenu: [1, [Validators.required, Validators.min(1)]],
      idModulo: [null, [Validators.required, Validators.min(1)]],
    });
  }

  cargarLista(): void {
    this.cargando.set(true);
    this.error.set('');

    this.menuService.buscarTodos().subscribe({
      next: (data) => {
        this.lista.set(data ?? []);
        this.cargando.set(false);
      },
      error: () => {
        this.error.set('Error al cargar los menús');
        this.cargando.set(false);
      },
    });
  }

  cargarModulos(): void {
    this.moduloService.buscarTodos().subscribe({
      next: (data) => {
        this.modulos.set(data ?? []);
      },
      error: () => {
        this.error.set('Error al cargar los módulos');
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

    const datos: MenuModel = {
      nombre: this.form.value.nombre,
      ordenMenu: this.form.value.ordenMenu,
      idModulo: Number(this.form.value.idModulo),
      usuarioCreacion: usuarioId,
    };

    this.cargando.set(true);
    this.mensaje.set('');
    this.error.set('');

    if (this.editando && this.idEditando) {
      datos.usuarioModif = usuarioId;
      this.menuService.actualizar(this.idEditando, datos).subscribe({
        next: () => this.finalizarOperacion('Menú actualizado correctamente'),
        error: () => {
          this.error.set('Error al actualizar el menú');
          this.cargando.set(false);
        },
      });
    } else {
      this.menuService.crear(datos).subscribe({
        next: () => this.finalizarOperacion('Menú creado correctamente'),
        error: () => {
          this.error.set('Error al crear el menú');
          this.cargando.set(false);
        },
      });
    }
  }

  editar(item: MenuModel): void {
    if (!this.puedeEditar) return;

    this.editando = true;
    this.idEditando = item.idMenu!;
    this.form.patchValue({
      nombre: item.nombre,
      ordenMenu: item.ordenMenu ?? 1,
      idModulo: item.idModulo ?? null,
    });
    window.scrollTo({ top: 0, behavior: 'smooth' });
  }

  eliminar(id: number): void {
    if (!this.puedeEliminar) return;

    if (!confirm('¿Está seguro de eliminar este menú?')) return;

    this.cargando.set(true);
    this.menuService.eliminar(id).subscribe({
      next: () => this.finalizarOperacion('Menú eliminado correctamente'),
      error: () => {
        this.error.set('Error al eliminar el menú');
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
      idModulo: null,
    });
    this.editando = false;
    this.idEditando = null;
    this.cargando.set(false);
  }

  get f() {
    return this.form.controls;
  }
}
