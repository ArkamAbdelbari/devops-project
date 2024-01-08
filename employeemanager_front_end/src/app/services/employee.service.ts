import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class EmployeeService  {
  private baseUrl = 'http://localhost:8080/employee';
  
  constructor(private _http: HttpClient) {}

  addEmployee(data: any): Observable<any> {
    return this._http.post('http://localhost:8080/employee/add', data);
  }

  updateEmployee(id: number, data: any): Observable<any> {
    return this._http.put(`http://localhost:8080/employee/update${id}`, data);
  }

  getEmployeeList(): Observable<any> {
    return this._http.get('http://localhost:8080/employee/all');
  }

  deleteEmployee(id: number): Observable<any> {
    return this._http.delete(`http://localhost:4200/employee/delete${id}`);
  }
}
