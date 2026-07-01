import { Injectable, signal, computed } from '@angular/core';
import { Task, TaskStatus, TaskPriority } from '../models/task.model';

@Injectable({ providedIn: 'root' })
export class TaskService {
  private nextId = signal(4);

  private _tasks = signal<Task[]>([
    {
      id: 1,
      title: 'Design landing page',
      description: 'Create wireframes and mockups for the new landing page.',
      status: 'completed',
      priority: 'high',
      dueDate: '2025-06-01',
      createdAt: new Date().toISOString(),
    },
    {
      id: 2,
      title: 'Integrate payment gateway',
      description: 'Connect Stripe API with the checkout flow.',
      status: 'in-progress',
      priority: 'high',
      dueDate: '2025-06-20',
      createdAt: new Date().toISOString(),
    },
    {
      id: 3,
      title: 'Write unit tests',
      description: 'Cover all service methods with Jest unit tests.',
      status: 'pending',
      priority: 'medium',
      dueDate: '2025-06-30',
      createdAt: new Date().toISOString(),
    },
  ]);

  readonly tasks = this._tasks.asReadonly();

  readonly stats = computed(() => {
    const all = this._tasks();
    return {
      total: all.length,
      pending: all.filter(t => t.status === 'pending').length,
      inProgress: all.filter(t => t.status === 'in-progress').length,
      completed: all.filter(t => t.status === 'completed').length,
    };
  });

  getById(id: number): Task | undefined {
    return this._tasks().find(t => t.id === id);
  }

  add(data: Omit<Task, 'id' | 'createdAt'>): Task {
    const task: Task = {
      ...data,
      id: this.nextId(),
      createdAt: new Date().toISOString(),
    };
    this._tasks.update(tasks => [...tasks, task]);
    this.nextId.update(n => n + 1);
    return task;
  }

  update(id: number, data: Partial<Omit<Task, 'id' | 'createdAt'>>): void {
    this._tasks.update(tasks =>
      tasks.map(t => (t.id === id ? { ...t, ...data } : t))
    );
  }

  delete(id: number): void {
    this._tasks.update(tasks => tasks.filter(t => t.id !== id));
  }
}