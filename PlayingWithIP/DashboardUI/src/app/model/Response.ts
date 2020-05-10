import { IPModel } from './IPModel';

export class FileUploadResponse {
  username: string;
  ipModels: Array<IPModel>;
  filename: string;
}
