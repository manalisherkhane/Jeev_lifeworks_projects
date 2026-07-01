import { Component, OnInit, inject, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router, ActivatedRoute, RouterLink } from '@angular/router';
import { TaskService } from '../../services/task.service';

@Component({
  selector: 'app-task-form',
  standalone: true,
  imports: [ReactiveFormsModule,CommonModule, RouterLink],
  templateUrl: './task-form.component.html',
  styleUrls: ['./task-form.component.css'],
})
export class TaskFormComponent implements OnInit {
  private fb = inject(FormBuilder);
  private router = inject(Router);
  private route = inject(ActivatedRoute);
  private taskService = inject(TaskService);

  isEdit = signal(false);
  taskId = signal<number | null>(null);
  submitted = signal(false);

  form: FormGroup = this.fb.group({
    title: ['', [Validators.required, Validators.minLength(3), Validators.maxLength(100)]],
    description: ['', [Validators.required, Validators.minLength(10)]],
    status: ['pending', Validators.required],
    priority: ['medium', Validators.required],
    dueDate: ['', Validators.required],
  });

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      const task = this.taskService.getById(Number(id));
      if (task) {
        this.isEdit.set(true);
        this.taskId.set(task.id);
        this.form.patchValue(task);
      } else {
        this.router.navigate(['/tasks']);
      }
    }
  }

  get f() { return this.form.controls; }

  fieldError(field: string): string | null {
    const ctrl = this.form.get(field);
    if (!ctrl || !(ctrl.touched || this.submitted())) return null;
    if (ctrl.hasError('required'))    return 'This field is required.';
    if (ctrl.hasError('minlength'))   return `Minimum ${ctrl.errors!['minlength'].requiredLength} characters.`;
    if (ctrl.hasError('maxlength'))   return `Maximum ${ctrl.errors!['maxlength'].requiredLength} characters.`;
    return null;
  }

  submit(): void {
    this.submitted.set(true);
    if (this.form.invalid) return;

    const data = this.form.value;
    if (this.isEdit()) {
      this.taskService.update(this.taskId()!, data);
    } else {
      this.taskService.add(data);
    }
    this.router.navigate(['/tasks']);
  }
}