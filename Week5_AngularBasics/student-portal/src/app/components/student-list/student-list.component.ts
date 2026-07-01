import { Component, inject } from '@angular/core';
import { RouterLink } from '@angular/router';
import { StudentService } from '../../services/student.service';

@Component({
  selector: 'app-student-list',
  standalone: true,
  imports: [RouterLink],
  templateUrl: './student-list.html',
  styleUrls: ['./student-list.css']
})
export class StudentListComponent {

  private svc = inject(StudentService);

  readonly students = this.svc.students;

  delete(id: number): void {
    if (confirm('Delete this student?')) {
      this.svc.delete(id);
    }
  }
}