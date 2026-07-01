import { Component, computed, signal, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';
import { TaskService } from '../../services/task.service';
import { Task, TaskStatus, TaskPriority } from '../../models/task.model';

@Component({
  selector: 'app-task-list',
  standalone: true,
  imports: [RouterLink,CommonModule],
  templateUrl: './task-list.component.html',
  styleUrls: ['./task-list.component.css'],
})
export class TaskListComponent {
  private taskService = inject(TaskService);

  readonly stats = this.taskService.stats;
  readonly filterStatus = signal<TaskStatus | 'all'>('all');
  readonly filterPriority = signal<TaskPriority | 'all'>('all');
  readonly searchQuery = signal('');

  readonly filteredTasks = computed(() => {
    let tasks = this.taskService.tasks();
    const status = this.filterStatus();
    const priority = this.filterPriority();
    const query = this.searchQuery().toLowerCase().trim();

    if (status !== 'all') tasks = tasks.filter(t => t.status === status);
    if (priority !== 'all') tasks = tasks.filter(t => t.priority === priority);
    if (query) tasks = tasks.filter(t =>
      t.title.toLowerCase().includes(query) ||
      t.description.toLowerCase().includes(query)
    );
    return tasks;
  });

  setStatusFilter(status: TaskStatus | 'all') { this.filterStatus.set(status); }
  setPriorityFilter(p: TaskPriority | 'all') { this.filterPriority.set(p); }
  setSearch(value: string) { this.searchQuery.set(value); }

  deleteTask(id: number) {
    if (confirm('Delete this task? This cannot be undone.')) {
      this.taskService.delete(id);
    }
  }

  priorityClass(p: TaskPriority): string {
    return { low: 'p-low', medium: 'p-med', high: 'p-high' }[p];
  }

  statusClass(s: TaskStatus): string {
    return { pending: 's-pending', 'in-progress': 's-progress', completed: 's-done' }[s];
  }

  statusLabel(s: TaskStatus): string {
    return { pending: 'Pending', 'in-progress': 'In Progress', completed: 'Completed' }[s];
  }
}