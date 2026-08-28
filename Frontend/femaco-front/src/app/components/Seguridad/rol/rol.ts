import { CommonModule } from '@angular/common';
import { Component, inject, OnInit, signal } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { UsuariosEmailPipe } from '../../../core/pipes/usuarios-email';
import { RolService } from '../../../core/services/rol.service';
import { AuthService } from '../../../core/services/auth.service';
import { UsuarioService } from '../../../core/services/usuario.service';
import { Rol } from '../../../core/models/rol.model';
import { ConjuntoMenuService } from '../../../core/services/conjunto-menu.service';

@Component({
  selector: 'app-rol',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, UsuariosEmailPipe],
  templateUrl: './rol.html',
  styleUrl: './rol.css',
})
export class rol implements OnInit {

  private fb = inject(FormBuilder);
  private rolService = inject(RolService);
  private authService = inject(AuthService);
  private usuarioService = inject(UsuarioService);
  private conjuntoMenuService = inject(ConjuntoMenuService);

  form!: FormGroup;

  lista = signal<Rol[]>([]);
  cargando = signal(false);
  mensaje = signal('');
  error = signal('');

  editando = false;
  idEditando: number | null = null;

  get permisos() {
    return this.conjuntoMenuService.getPermisosPorPagina('rol');
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
    this.usuarioService.loadEmailCache().subscribe();
  }

  private initForm(): void {
    this.form = this.fb.group({
      nombre: ['', [Validators.required, Validators.maxLength(100)]],
      ordenMenu: [1, [Validators.required, Validators.min(1)]]
    });
  }

  cargarLista(): void {
    this.cargando.set(true);
    this.error.set('');

    this.rolService.buscarTodos().subscribe({
      next: (data) => {
        this.lista.set(data ?? []);
        this.cargando.set(false);
      },
      error: () => {
        this.error.set('Error al cargar los Rol');
        this.cargando.set(false);
      }
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

    const datos: Rol = {
      nombre: this.form.value.nombre,
      usuarioCreacion: usuarioId
    };

    this.cargando.set(true);
    this.mensaje.set('');
    this.error.set('');

    if (this.editando && this.idEditando) {
      datos.usuarioModif = usuarioId;
      this.rolService.actualizar(this.idEditando, datos).subscribe({
        next: () => {
          this.finalizarOperacion('Rol actualizado correctamente');
        },
        error: () => {
          this.error.set('Error al actualizar el Rol');
          this.cargando.set(false);
        }
      });
    } else {
      this.rolService.crear(datos).subscribe({
        next: () => {
          this.finalizarOperacion('Rol creado correctamente');
        },
        error: () => {
          this.error.set('Error al crear el Rol');
          this.cargando.set(false);
        }
      });
    }
  }

  editar(item: Rol): void {
    if (!this.puedeEditar) return;

    this.editando = true;
    this.idEditando = item.idRol!;
    this.form.patchValue({
      nombre: item.nombre,
      ordenMenu: 1,
    });
    window.scrollTo({ top: 0, behavior: 'smooth' });
  }

  eliminar(id: number): void {
    if (!this.puedeEliminar) return;

    if (!confirm('¿Está seguro de eliminar este Rol?')) return;

    this.cargando.set(true);
    this.rolService.eliminar(id).subscribe({
      next: () => {
        this.finalizarOperacion('Rol eliminado correctamente');
      },
      error: () => {
        this.error.set('Error al eliminar el Rol');
        this.cargando.set(false);
      }
    });
  }

  cancelar(): void {
    this.resetForm();
  }

  private resetForm(): void {
    this.form.reset({ nombre: '', ordenMenu: 1 });
    this.editando = false;
    this.idEditando = null;
    this.cargando.set(false);
  }

  get f() {
    return this.form.controls;
  }

}
