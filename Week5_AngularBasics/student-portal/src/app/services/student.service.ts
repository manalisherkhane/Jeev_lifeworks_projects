import { Injectable, signal } from '@angular/core';
import { Student } from '../models/student.model';

@Injectable({ providedIn: 'root' })
export class StudentService {
  private nextId = 4;

  private _students = signal<Student[]>([
    { id: 1, name: 'Arjun Sharma', email: 'arjun@mail.com', course: 'Computer Science', grade: 'A', enrollmentDate: '2024-01-15' },
    { id: 2, name: 'Priya Nair',   email: 'priya@mail.com',  course: 'Data Science',     grade: 'B+', enrollmentDate: '2024-02-10' },
    { id: 3, name: 'Rahul Verma',  email: 'rahul@mail.com',  course: 'Cybersecurity',    grade: 'A-', enrollmentDate: '2024-03-05' },
  ]);

  readonly students = this._students.asReadonly();

  getById(id: number): Student | undefined {
    return this._students().find(s => s.id === id);
  }

  add(student: Omit<Student, 'id'>): void {
    this._students.update(list => [...list, { ...student, id: this.nextId++ }]);
  }

  update(updated: Student): void {
    this._students.update(list => list.map(s => s.id === updated.id ? updated : s));
  }

  delete(id: number): void {
    this._students.update(list => list.filter(s => s.id !== id));
  }
}