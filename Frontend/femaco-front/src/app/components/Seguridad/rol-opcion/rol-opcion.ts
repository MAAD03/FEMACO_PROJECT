import { CommonModule } from '@angular/common';
import { Component, inject, OnInit, signal } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Opcion } from '../../../core/models/opcion.model';
import { Rol } from '../../../core/models/rol.model';
import { RolOpcion } from '../../../core/models/rol-opcion.model';
import { OpcionNombrePipe } from '../../../core/pipes/opcion-nombre-pipe';
import { RolNombrePipe } from '../../../core/pipes/rol-nombre-pipe';
import { AuthService } from '../../../core/services/auth.service';
import { ConjuntoMenuService } from '../../../core/services/conjunto-menu.service';
import { OpcionService } from '../../../core/services/opcion.service';
import { RolOpcionService } from '../../../core/services/rol-opcion.service';
import { RolService } from '../../../core/services/rol.service';

@Component({
  selector: 'app-rol-opcion',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, RolNombrePipe, OpcionNombrePipe],
  templateUrl: './rol-opcion.html',
  styleUrl: './rol-opcion.css',
})
export class RolOpcionComponent implements OnInit {
  private fb = inject(FormBuilder);
  private rolOpcionService = inject(RolOpcionService);
  private authService = inject(AuthService);
  private conjuntoMenuService = inject(ConjuntoMenuService);
  private rolService = inject(RolService);
  private opcionService = inject(OpcionService);

  form!: FormGroup;

  lista = signal<RolOpcion[]>([]);
  roles = signal<Rol[]>([]);
  opciones = signal<Opcion[]>([]);
  cargando = signal(false);
  mensaje = signal('');
  error = signal('');

  editando = false;
  idEditando: number | null = null;

  permisosPorRol() {
    const grupos = this.roles()
      .map((rol) => ({
        rol,
        permisos: this.lista().filter((permiso) => permiso.idRol === rol.idRol),
      }))
      .filter((grupo) => grupo.permisos.length > 0);

    const sinRol = this.lista().filter(
      (permiso) => !permiso.idRol || !this.roles().some((rol) => rol.idRol === permiso.idRol),
    );

    if (sinRol.length > 0) {
      grupos.push({
        rol: { idRol: 0, nombre: 'Sin rol asignado' } as Rol,
        permisos: sinRol,
      });
    }

    return grupos;
  }

  get permisos() {
    return this.conjuntoMenuService.getPermisosPorPagina('rol-opcion');
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
    this.cargarRoles();
    this.cargarOpciones();
    this.rolService.loadNombreCache().subscribe();
    this.opcionService.loadNombreCache().subscribe();
  }

  private initForm(): void {
    this.form = this.fb.group({
      idRol: [null, [Validators.required, Validators.min(1)]],
      idOpcion: [null, [Validators.required, Validators.min(1)]],
      alta: [false],
      baja: [false],
      cambio: [false],
    });
  }

  cargarLista(): void {
    this.cargando.set(true);
    this.error.set('');

    this.rolOpcionService.buscarTodos().subscribe({
      next: (data) => {
        this.lista.set(data ?? []);
        this.cargando.set(false);
      },
      error: () => {
        this.error.set('Error al cargar los permisos');
        this.cargando.set(false);
      },
    });
  }

  cargarRoles(): void {
    this.rolService.buscarTodos().subscribe({
      next: (data) => {
        this.roles.set(data ?? []);
      },
      error: () => {
        this.error.set('Error al cargar los roles');
      },
    });
  }

  cargarOpciones(): void {
    this.opcionService.buscarTodos().subscribe({
      next: (data) => {
        this.opciones.set(data ?? []);
      },
      error: () => {
        this.error.set('Error al cargar las opciones');
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

    const datos: RolOpcion = {
      idRol: Number(this.form.value.idRol),
      idOpcion: Number(this.form.value.idOpcion),
      alta: !!this.form.value.alta,
      baja: !!this.form.value.baja,
      cambio: !!this.form.value.cambio,
      usuarioCreacion: usuarioId,
    };

    this.cargando.set(true);
    this.mensaje.set('');
    this.error.set('');

    if (this.editando && this.idEditando) {
      datos.usuarioModif = usuarioId;
      this.rolOpcionService.actualizar(this.idEditando, datos).subscribe({
        next: () => {
          this.finalizarOperacion('Permiso actualizado correctamente');
        },
        error: () => {
          this.error.set('Error al actualizar el permiso');
          this.cargando.set(false);
        },
      });
    } else {
      this.rolOpcionService.crear(datos).subscribe({
        next: () => {
          this.finalizarOperacion('Permiso creado correctamente');
        },
        error: () => {
          this.error.set('Error al crear el permiso');
          this.cargando.set(false);
        },
      });
    }
  }

  editar(item: RolOpcion): void {
    if (!this.puedeEditar) return;

    this.editando = true;
    this.idEditando = item.idRolOpcion!;
    this.form.patchValue({
      idRol: item.idRol ?? null,
      idOpcion: item.idOpcion ?? null,
      alta: !!item.alta,
      baja: !!item.baja,
      cambio: !!item.cambio,
    });
    window.scrollTo({ top: 0, behavior: 'smooth' });
  }

  eliminar(id: number): void {
    if (!this.puedeEliminar) return;

    if (!confirm('¿Está seguro de eliminar este permiso?')) return;

    this.cargando.set(true);
    this.rolOpcionService.eliminar(id).subscribe({
      next: () => {
        this.finalizarOperacion('Permiso eliminado correctamente');
      },
      error: () => {
        this.error.set('Error al eliminar el permiso');
        this.cargando.set(false);
      },
    });
  }

  cancelar(): void {
    this.resetForm();
  }

  private resetForm(): void {
    this.form.reset({
      idRol: null,
      idOpcion: null,
      alta: false,
      baja: false,
      cambio: false,
    });
    this.editando = false;
    this.idEditando = null;
    this.cargando.set(false);
  }

  get f() {
    return this.form.controls;
  }
}
