import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { FileUploadResponse } from '../model/Response';

@Injectable({
 providedIn: 'root'
})
export class UploadService {
 private fileList: string[] = new Array<string>();
  baseUrl: string = "http://localhost:8080/uploadFile";

  constructor(private httpClient: HttpClient) { }

  public upload(formData: FormData): Observable<any> {
    const header= new HttpHeaders();
    header.set('Access-Control-Allow-Origin', '*');
    header.set('Access-Control-Allow-Methods','PUT, GET, POST');
    header.set('Access-Control-Allow-Header','X-Requested-With, Content-Type, Accept');
    return this.httpClient.post(this.baseUrl, formData);
  }

}
