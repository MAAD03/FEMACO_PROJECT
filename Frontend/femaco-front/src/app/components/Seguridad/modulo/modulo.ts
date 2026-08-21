import { Component, inject, OnInit, signal } from '@angular/core';
import { ModuloService } from '../../../core/services/modulo.service';
import { Modulo } from '../../../core/models/modulo.model';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { AuthService } from '../../../core/services/auth.service';
import { CommonModule } from '@angular/common';
import { UsuariosEmailPipe } from '../../../core/pipes/usuarios-email';
import { UsuarioService } from '../../../core/services/usuario.service';

@Component({
  selector: 'app-modulo',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, UsuariosEmailPipe],
  templateUrl: './modulo.html',
  styleUrl: './modulo.css',
})
export class modulo implements OnInit {
  private fb = inject(FormBuilder);
  private moduloService = inject(ModuloService);
  private authService = inject(AuthService);
  private usuarioService = inject(UsuarioService);

  form!: FormGroup;

  // Signals (reactivos)
  lista = signal<Modulo[]>([]);
  cargando = signal(false);
  mensaje = signal('');
  error = signal('');

  editando = false;
  idEditando: number | null = null;

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

    this.moduloService.buscarTodos().subscribe({
      next: (data) => {
        this.lista.set(data ?? []);
        this.cargando.set(false);
      },
      error: () => {
        this.error.set('Error al cargar los módulos');
        this.cargando.set(false);
      }
    });
  }

  private finalizarOperacion(mensaje: string): void {
    this.mensaje.set(mensaje);
    this.resetForm();
    this.cargarLista();

    // Limpia el mensaje de éxito después de 3.5 segundos
    setTimeout(() => {
      this.mensaje.set('');
    }, 3500);
  }

  onSubmit(): void {
    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }

    const usuarioId = this.authService.getCurrentUser()?.idUsuario;
    if (!usuarioId) {
      this.error.set('No se encontró el usuario autenticado');
      return;
    }

    const datos: Modulo = {
      nombre: this.form.value.nombre,
      ordenMenu: this.form.value.ordenMenu,
      usuarioCreacion: usuarioId
    };

    this.cargando.set(true);
    this.mensaje.set('');
    this.error.set('');

    if (this.editando && this.idEditando) {
      // Actualizar
      datos.usuarioModif = usuarioId;
      this.moduloService.actualizar(this.idEditando, datos).subscribe({
        next: () => {
          this.finalizarOperacion('Módulo actualizado correctamente');
        },
        error: () => {
          this.error.set('Error al actualizar el módulo');
          this.cargando.set(false);
        }
      });
    } else {
      // Crear
      this.moduloService.crear(datos).subscribe({
        next: () => {
          this.finalizarOperacion('Módulo creado correctamente');
        },
        error: () => {
          this.error.set('Error al crear el módulo');
          this.cargando.set(false);
        }
      });
    }
  }

  editar(item: Modulo): void {
    this.editando = true;
    this.idEditando = item.idModulo!;
    this.form.patchValue({
      nombre: item.nombre,
      ordenMenu: item.ordenMenu
    });
    window.scrollTo({ top: 0, behavior: 'smooth' });
  }

  eliminar(id: number): void {
    if (!confirm('¿Está seguro de eliminar este módulo?')) return;

    this.cargando.set(true);
    this.moduloService.eliminar(id).subscribe({
      next: () => {
        this.finalizarOperacion('Módulo eliminado correctamente');
      },
      error: () => {
        this.error.set('Error al eliminar el módulo');
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