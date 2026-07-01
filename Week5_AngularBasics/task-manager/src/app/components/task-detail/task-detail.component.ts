import { Component, OnInit, inject, signal } from '@angular/core';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { TaskService } from '../../services/task.service';
import { Task, TaskStatus } from '../../models/task.model';
import { CommonModule, DatePipe } from '@angular/common';



@Component({
    
  selector: 'app-task-detail',
  standalone: true,
  imports: [RouterLink, CommonModule,DatePipe],
  templateUrl: './task-detail.component.html',
  styleUrls: ['./task-detail.component.css'],
})
export class TaskDetailComponent implements OnInit {
  private route = inject(ActivatedRoute);
  private router = inject(Router);
  private taskService = inject(TaskService);

  task = signal<Task | null>(null);

  ngOnInit(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    const found = this.taskService.getById(id);
    if (!found) { this.router.navigate(['/tasks']); return; }
    this.task.set(found);
  }

  delete(): void {
    if (confirm('Delete this task?')) {
      this.taskService.delete(this.task()!.id);
      this.router.navigate(['/tasks']);
    }
  }

  statusLabel(s: TaskStatus): string {
    return { pending: 'Pending', 'in-progress': 'In Progress', completed: 'Completed' }[s];
  }

  statusClass(s: TaskStatus): string {
    return { pending: 's-pending', 'in-progress': 's-progress', completed: 's-done' }[s];
  }
}