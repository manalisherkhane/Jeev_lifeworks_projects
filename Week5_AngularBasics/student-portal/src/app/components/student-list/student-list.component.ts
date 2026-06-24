import { Component, inject } from '@angular/core';
import { RouterLink } from '@angular/router';
import { StudentService } from '../../services/student.service';
import { Student } from '../../models/student.model';

@Component({
  selector: 'app-student-list',
  standalone: true,
  imports: [RouterLink],
  template: `
    <div class="header">
      <h2>All Students <span class="count">{{ students().length }}</span></h2>
      <a routerLink="/students/new" class="btn-primary">+ Add Student</a>
    </div>

    @if (students().length === 0) {
      <p class="empty">No students found. Add one to get started.</p>
    } @else {
      <table class="table">
        <thead>
          <tr>
            <th>Name</th>
            <th>Email</th>
            <th>Course</th>
            <th>Grade</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          @for (student of students(); track student.id) {
            <tr>
              <td><a [routerLink]="['/students', student.id]" class="name-link">{{ student.name }}</a></td>
              <td>{{ student.email }}</td>
              <td>{{ student.course }}</td>
              <td><span class="badge">{{ student.grade }}</span></td>
              <td class="actions">
                <a [routerLink]="['/students/edit', student.id]" class="btn-edit">Edit</a>
                <button (click)="delete(student.id)" class="btn-delete">Delete</button>
              </td>
            </tr>
          }
        </tbody>
      </table>
    }
  `,
})
export class StudentListComponent {
  private svc = inject(StudentService);
  readonly students = this.svc.students;

  delete(id: number): void {
    if (confirm('Delete this student?')) this.svc.delete(id);
  }
}