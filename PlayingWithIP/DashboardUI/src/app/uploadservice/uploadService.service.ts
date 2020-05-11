import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { FileUploadResponse, ResponseAdapter } from '../model/ResponseModel';
import { catchError, tap, map } from 'rxjs/operators';

@Injectable({
 providedIn: 'root'
})
export class UploadService {
 private fileList: string[] = new Array<string>();
  baseUrl: string = "http://localhost:8080/uploadFile";

  constructor(private httpClient: HttpClient, private adapter: ResponseAdapter) { }

  public upload(formData: FormData): Observable<FileUploadResponse[]> {
    const header= new HttpHeaders();
    header.set('Access-Control-Allow-Origin', '*');
    header.set('Access-Control-Allow-Methods','PUT, GET, POST');
    header.set('Access-Control-Allow-Header','X-Requested-With, Content-Type, Accept');
    return this.httpClient.post<FileUploadResponse[]>(this.baseUrl, formData).pipe(
      map((data: any[]) =>
        data.map(item => this.adapter.adapt(item))
        )
    );
  }

}
