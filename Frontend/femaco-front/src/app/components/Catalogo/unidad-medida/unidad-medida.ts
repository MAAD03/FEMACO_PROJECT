import { CommonModule } from '@angular/common';
import { Component, OnInit, inject, signal } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { UnidadMedida as UnidadMedidaModel } from '../../../core/models/unidad-medida.model';
import { UsuariosEmailPipe } from '../../../core/pipes/usuarios-email';
import { AuthService } from '../../../core/services/auth.service';
import { ConjuntoMenuService } from '../../../core/services/conjunto-menu.service';
import { UnidadMedidaService } from '../../../core/services/unidad-medida.service';
import { UsuarioService } from '../../../core/services/usuario.service';

@Component({
  selector: 'app-unidad-medida',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, UsuariosEmailPipe],
  templateUrl: './unidad-medida.html',
  styleUrl: './unidad-medida.css',
})
export class UnidadMedida implements OnInit {
  private readonly fb = inject(FormBuilder);
  private readonly unidadMedidaService = inject(UnidadMedidaService);
  private readonly authService = inject(AuthService);
  private readonly conjuntoMenuService = inject(ConjuntoMenuService);
  private readonly usuarioService = inject(UsuarioService);

  form!: FormGroup;

  lista = signal<UnidadMedidaModel[]>([]);
  cargando = signal(false);
  mensaje = signal('');
  error = signal('');
  editando = false;
  idEditando: number | null = null;

  get permisos() {
    return this.conjuntoMenuService.getPermisosPorPagina('unidad-medida');
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
      abreviatura: ['', [Validators.required, Validators.maxLength(20)]],
    });
  }

  cargarLista(): void {
    this.cargando.set(true);
    this.error.set('');

    this.unidadMedidaService.buscarTodos().subscribe({
      next: (data) => {
        this.lista.set(data ?? []);
        this.cargando.set(false);
      },
      error: () => {
        this.error.set('Error al cargar las unidades de medida');
        this.cargando.set(false);
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

    const datos: UnidadMedidaModel = {
      nombre: this.form.value.nombre,
      abreviatura: this.form.value.abreviatura,
      usuarioCreacion: usuarioId,
    };

    this.cargando.set(true);
    this.mensaje.set('');
    this.error.set('');

    if (this.editando && this.idEditando) {
      datos.usuarioModif = usuarioId;
      this.unidadMedidaService.actualizar(this.idEditando, datos).subscribe({
        next: () => this.finalizarOperacion('Unidad de medida actualizada correctamente'),
        error: () => {
          this.error.set('Error al actualizar la unidad de medida');
          this.cargando.set(false);
        },
      });
    } else {
      this.unidadMedidaService.crear(datos).subscribe({
        next: () => this.finalizarOperacion('Unidad de medida creada correctamente'),
        error: () => {
          this.error.set('Error al crear la unidad de medida');
          this.cargando.set(false);
        },
      });
    }
  }

  editar(item: UnidadMedidaModel): void {
    if (!this.puedeEditar) return;

    this.editando = true;
    this.idEditando = item.idUnidadMedida!;
    this.form.patchValue({
      nombre: item.nombre,
      abreviatura: item.abreviatura,
    });
    window.scrollTo({ top: 0, behavior: 'smooth' });
  }

  eliminar(id: number): void {
    if (!this.puedeEliminar) return;

    if (!confirm('¿Está seguro de eliminar esta unidad de medida?')) return;

    this.cargando.set(true);
    this.unidadMedidaService.eliminar(id).subscribe({
      next: () => this.finalizarOperacion('Unidad de medida eliminada correctamente'),
      error: () => {
        this.error.set('Error al eliminar la unidad de medida');
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
      abreviatura: '',
    });
    this.editando = false;
    this.idEditando = null;
    this.cargando.set(false);
  }

  get f() {
    return this.form.controls;
  }
}
