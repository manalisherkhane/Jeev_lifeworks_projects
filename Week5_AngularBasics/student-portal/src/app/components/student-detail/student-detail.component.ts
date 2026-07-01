import { Component, inject, OnInit, signal } from '@angular/core';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { StudentService } from '../../services/student.service';
import { Student } from '../../models/student.model';

@Component({
  selector: 'app-student-detail',
  standalone: true,
  imports: [RouterLink],
  templateUrl: './student-detail.html',
  styleUrls: ['./student-detail.css']
})
export class StudentDetailComponent implements OnInit {

  private route = inject(ActivatedRoute);
  private svc = inject(StudentService);
  private router = inject(Router);

  student = signal<Student | undefined>(undefined);

  initials = () => {
    const name = this.student()?.name ?? '';
    return name
      .split(' ')
      .map(p => p[0])
      .join('')
      .slice(0, 2)
      .toUpperCase();
  };

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');

    if (id) {
      this.student.set(this.svc.getById(+id));
    }
  }

  delete(): void {
    if (confirm('Delete this student?')) {
      this.svc.delete(this.student()!.id);
      this.router.navigate(['/students']);
    }
  }
}