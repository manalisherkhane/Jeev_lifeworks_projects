import { Component, inject, OnInit } from '@angular/core';
import { ReactiveFormsModule, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { StudentService } from '../../services/student.service';

@Component({
  selector: 'app-student-form',
  standalone: true,
  imports: [ReactiveFormsModule, RouterLink],
  template: `
    <div class="form-card">
      <h2>{{ isEdit ? 'Edit Student' : 'Add New Student' }}</h2>

      <form [formGroup]="form" (ngSubmit)="submit()">
        <div class="field">
          <label>Full Name</label>
          <input formControlName="name" placeholder="e.g. Arjun Sharma" />
          @if (f['name'].touched && f['name'].invalid) {
            <span class="error">Name is required.</span>
          }
        </div>

        <div class="field">
          <label>Email</label>
          <input formControlName="email" type="email" placeholder="e.g. arjun@mail.com" />
          @if (f['email'].touched && f['email'].invalid) {
            <span class="error">Valid email is required.</span>
          }
        </div>

        <div class="field">
          <label>Course</label>
          <select formControlName="course">
            <option value="">-- Select Course --</option>
            <option>Computer Science</option>
            <option>Data Science</option>
            <option>Cybersecurity</option>
            <option>Web Development</option>
            <option>Cloud Computing</option>
          </select>
          @if (f['course'].touched && f['course'].invalid) {
            <span class="error">Course is required.</span>
          }
        </div>

        <div class="field">
          <label>Grade</label>
          <select formControlName="grade">
            <option value="">-- Select Grade --</option>
            <option>A</option>
            <option>A-</option>
            <option>B+</option>
            <option>B</option>
            <option>B-</option>
            <option>C</option>
          </select>
          @if (f['grade'].touched && f['grade'].invalid) {
            <span class="error">Grade is required.</span>
          }
        </div>

        <div class="field">
          <label>Enrollment Date</label>
          <input formControlName="enrollmentDate" type="date" />
          @if (f['enrollmentDate'].touched && f['enrollmentDate'].invalid) {
            <span class="error">Enrollment date is required.</span>
          }
        </div>

        <div class="actions">
          <a routerLink="/students" class="btn-cancel">Cancel</a>
          <button type="submit" [disabled]="form.invalid" class="btn-submit">
            {{ isEdit ? 'Update Student' : 'Add Student' }}
          </button>
        </div>
      </form>
    </div>
  `,
  styles: [`
    .form-card { max-width: 540px; margin: 0 auto; background: white; padding: 2rem; border-radius: 10px; box-shadow: 0 2px 12px rgba(0,0,0,0.08); }
    h2 { font-size: 1.4rem; font-weight: 700; color: #1a202c; margin-bottom: 1.5rem; }
    .field { display: flex; flex-direction: column; gap: 0.4rem; margin-bottom: 1.2rem; }
    label { font-size: 0.875rem; font-weight: 600; color: #4a5568; }
    input, select {
      padding: 0.6rem 0.75rem; border: 1px solid #e2e8f0; border-radius: 6px;
      font-size: 0.95rem; color: #2d3748; transition: border 0.2s;
    }
    input:focus, select:focus { outline: none; border-color: #63b3ed; box-shadow: 0 0 0 3px #ebf8ff; }
    .error { color: #e53e3e; font-size: 0.8rem; }
    .actions { display: flex; justify-content: flex-end; gap: 0.75rem; margin-top: 1.5rem; }
    .btn-cancel { padding: 0.5rem 1.25rem; border-radius: 6px; border: 1px solid #e2e8f0; color: #718096; text-decoration: none; font-size: 0.9rem; }
    .btn-submit { padding: 0.5rem 1.5rem; background: #3182ce; color: white; border: none; border-radius: 6px; font-size: 0.9rem; cursor: pointer; }
    .btn-submit:disabled { opacity: 0.5; cursor: not-allowed; }
    .btn-submit:not(:disabled):hover { background: #2b6cb0; }
  `],
})
export class StudentFormComponent implements OnInit {
  private fb     = inject(FormBuilder);
  private svc    = inject(StudentService);
  private router = inject(Router);
  private route  = inject(ActivatedRoute);

  isEdit = false;
  private editId?: number;

  form: FormGroup = this.fb.group({
    name:           ['', Validators.required],
    email:          ['', [Validators.required, Validators.email]],
    course:         ['', Validators.required],
    grade:          ['', Validators.required],
    enrollmentDate: ['', Validators.required],
  });

  get f() { return this.form.controls; }

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      const student = this.svc.getById(+id);
      if (student) {
        this.isEdit = true;
        this.editId = student.id;
        this.form.patchValue(student);
      }
    }
  }

  submit(): void {
    if (this.form.invalid) return;
    if (this.isEdit && this.editId !== undefined) {
      this.svc.update({ id: this.editId, ...this.form.value });
    } else {
      this.svc.add(this.form.value);
    }
    this.router.navigate(['/students']);
  }
}