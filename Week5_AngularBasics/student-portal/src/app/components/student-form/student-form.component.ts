import { Component, inject, OnInit } from '@angular/core';
import { ReactiveFormsModule, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { StudentService } from '../../services/student.service';

@Component({
  selector: 'app-student-form',
  standalone: true,
  imports: [ReactiveFormsModule, RouterLink],
  templateUrl: './student-form.html',
  styleUrls: ['./student-form.css']
})
export class StudentFormComponent implements OnInit {

  private fb = inject(FormBuilder);
  private svc = inject(StudentService);
  private router = inject(Router);
  private route = inject(ActivatedRoute);

  isEdit = false;
  private editId?: number;

  form: FormGroup = this.fb.group({
    name: ['', Validators.required],
    email: ['', [Validators.required, Validators.email]],
    course: ['', Validators.required],
    grade: ['', Validators.required],
    enrollmentDate: ['', Validators.required],
  });

  get f() {
    return this.form.controls;
  }

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
      this.svc.update({
        id: this.editId,
        ...this.form.value
      });
    } else {
      this.svc.add(this.form.value);
    }

    this.router.navigate(['/students']);
  }
}