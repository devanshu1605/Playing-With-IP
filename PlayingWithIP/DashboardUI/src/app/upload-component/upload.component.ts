import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators, FormGroup, FormControl } from '@angular/forms';
import { UploadService } from '../uploadservice/uploadService.service';
import { FileUploadResponse } from '../model/ResponseModel';

@Component({
  selector: 'app-uploader',
  templateUrl: './upload.component.html'
})
export class FileUploaderComponent {

  response: FileUploadResponse;

  public formGroup = this.formbuilder.group({
    file: [null, Validators.required]
  });

  responseRecieved: boolean = false;

  constructor(private formbuilder: FormBuilder, private uploadService: UploadService) { }

  myForm = new FormGroup({
    file: new FormControl('', [Validators.required]),
    fileSource: new FormControl('', [Validators.required])
  });

  get f() {
    return this.myForm.controls;
  }

  public onFileChange(event) {
    if (event.target.files.length > 0) {
      const file = event.target.files[0];
      this.myForm.patchValue({
        fileSource: file
      });
    }
  }

  public onSubmit(): void {
    const formData = new FormData();
    formData.append('file', this.myForm.get('fileSource').value);
    this.uploadService.upload(formData).subscribe(res => {
      this.response = res;
      this.responseRecieved = true;
      console.log(this.response);
      alert('Uploaded Successfully.');
    });
  }
}
