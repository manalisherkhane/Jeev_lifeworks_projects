import { Component, inject } from '@angular/core';
import { RouterLink } from '@angular/router';
import { StudentService } from '../../services/student.service';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [RouterLink],
  templateUrl: './dashboard.html',
  styleUrls: ['./dashboard.css']
})
export class DashboardComponent {

  private svc = inject(StudentService);

  students = this.svc.students;

  get totalStudents(): number {
    return this.students().length;
  }

  get totalCourses(): number {
    return new Set(
      this.students().map(s => s.course)
    ).size;
  }

  get topGradeStudents(): number {
    return this.students()
      .filter(s => s.grade.startsWith('A'))
      .length;
  }

  get recentStudents() {
    return this.students().slice(-5).reverse();
  }
}