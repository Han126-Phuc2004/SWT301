import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { PostService } from '../../services/post.service';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-post-form',
  templateUrl: './post-form.component.html',
  styleUrls: ['./post-form.component.css']
})
export class PostFormComponent implements OnInit {
  postForm: FormGroup;
  jobCategories: any[] = [];
  isSubmitting = false;

  constructor(
    private fb: FormBuilder,
    private postService: PostService,
    private router: Router,
    private toastr: ToastrService
  ) {
    this.postForm = this.fb.group({
      title: ['', [Validators.required, Validators.minLength(10)]],
      jcID: ['', Validators.required],
      company: ['', Validators.required],
      description: ['', [Validators.required, Validators.minLength(50)]],
      requirement: ['', [Validators.required, Validators.minLength(50)]],
      location: ['', Validators.required],
      salaryMin: ['', [Validators.required, Validators.min(0)]],
      salaryMax: [''],
      salaryCurrency: ['VND', Validators.required],
      workingTime: ['', Validators.required],
      jobType: ['', Validators.required]
    });
  }

  ngOnInit(): void {
    this.loadJobCategories();
  }

  loadJobCategories(): void {
    this.postService.getJobCategories().subscribe({
      next: (data) => {
        this.jobCategories = data;
      },
      error: (error) => {
        this.toastr.error('Không thể tải danh mục công việc');
        console.error('Error loading job categories:', error);
      }
    });
  }

  onSubmit(): void {
    if (this.postForm.valid) {
      this.isSubmitting = true;
      const postData = this.postForm.value;

      this.postService.createPost(postData).subscribe({
        next: (response) => {
          this.toastr.success('Đăng bài thành công!');
          this.router.navigate(['/posts']);
        },
        error: (error) => {
          this.toastr.error('Đăng bài thất bại. Vui lòng thử lại.');
          console.error('Error creating post:', error);
          this.isSubmitting = false;
        }
      });
    } else {
      this.toastr.warning('Vui lòng điền đầy đủ thông tin yêu cầu');
    }
  }
} 