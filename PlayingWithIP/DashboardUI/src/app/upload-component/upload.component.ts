import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators, FormGroup, FormControl } from '@angular/forms';
import { UploadService } from '../uploadservice/uploadService.service';

@Component({
  selector: 'app-uploader',
  templateUrl: './upload.component.html'
})
export class FileUploaderComponent {

  public formGroup = this.formbuilder.group({
    file: [null, Validators.required]
  });

  private fileName;

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
      console.log(res);
      alert('Uploaded Successfully.');
    });
  }
}
