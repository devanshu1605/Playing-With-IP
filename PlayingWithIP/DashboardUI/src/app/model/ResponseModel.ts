import { IPModel, Adapter } from './IPModel';
import { Injectable } from '@angular/core';

export class FileUploadResponse {
  constructor(
    sortedUsersMap: Map<string, number>,
    hourlyUserData: Map<string, number>,
    sortedFileMap: Map<string, number>
  ) { }
}

@Injectable({
  providedIn: "root"
})
export class ResponseAdapter implements Adapter<FileUploadResponse> {
  adapt(item: any): FileUploadResponse {
    return new FileUploadResponse(item.sortedUsersMap, item.hourlyUserData, item.top5File);
  }
}
