// src/app/features/dashboard/dashboard.component.ts
import { Component, inject, signal, computed } from '@angular/core';
import { ApiService, Post } from '../../core/services/api.service';
import { AuthService } from '../../core/services/auth.service';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  template: `
<div class="dashboard">

  <div class="top-bar">

    <div>
      <h1>📋 Dashboard</h1>
      <p>Welcome! Here are your latest posts.</p>
    </div>

    <button class="logout-btn" (click)="logout()">
      Logout
    </button>

  </div>

  <div class="stats">

    <div class="card">
      <h2>{{ posts().length }}</h2>
      <p>Total Posts</p>
    </div>

    <div class="card">
      <h2>✔</h2>
      <p>Authenticated</p>
    </div>

  </div>

<div class="posts-section">

  <h2>Latest Posts</h2>

@if(loading()){

<div class="loading">

Loading Posts...

</div>

}



  <input
    class="search-box"
    type="text"
    placeholder="🔍 Search Posts..."
    (input)="searchText.set($any($event.target).value)"
  >

 @for(post of filteredPosts(); track post.id){

    <div class="post-card">

      <h3>{{ post.title }}</h3>

      <p>{{ post.body }}</p>

    </div>

  }

</div>

</div>
`,
 styles: [`
.dashboard{
padding:40px;
background:#f5f7fb;
min-height:100vh;
font-family:Arial,sans-serif;
}

.top-bar{

display:flex;
justify-content:space-between;
align-items:center;
margin-bottom:30px;
flex-wrap:wrap;
gap:15px;
}

.search-box{

width:100%;
padding:12px;
margin:20px 0;
border:1px solid #ccc;
border-radius:8px;
font-size:16px;
box-sizing:border-box;
}

.top-bar h1{

margin:0;
color:#333;
}

.top-bar p{

margin-top:5px;
color:#666;
}

.logout-btn{

background:#ef4444;
color:white;
padding:12px 22px;
border:none;
border-radius:8px;
cursor:pointer;
font-size:15px;
transition:.3s;
}

.logout-btn:hover{
background:#dc2626;
}

.stats{
display:flex;
gap:20px;
margin-bottom:30px;
flex-wrap:wrap;
}

.card{
flex:1;
min-width:220px;
background:white;
padding:25px;
border-radius:12px;
box-shadow:0 5px 15px rgba(0,0,0,.1);
text-align:center;
}

.card h2{
margin:0;
color:#2563eb;
font-size:35px;
}

.posts-section h2{
margin-bottom:20px;
}

.post-card{
background:white;
padding:20px;
margin-bottom:20px;
border-radius:12px;
box-shadow:0 5px 12px rgba(0,0,0,.08);
transition:.3s;

}

.post-card:hover{
transform:translateY(-4px);
}

.post-card h3{
text-transform:capitalize;
margin-bottom:10px;
color:#222;

}
.post-card p{
color:#555;
line-height:1.6;
}

@media(max-width:768px){

.top-bar{
flex-direction:column;
align-items:flex-start;
}

.stats{
flex-direction:column;
}

.loading{
text-align:center;
padding:20px;
font-size:18px;
font-weight:bold;
color:#2563eb;
}

}

`]
})
export class DashboardComponent {
  private api = inject(ApiService);
  private authService = inject(AuthService);
  private router = inject(Router);

  posts = signal<Post[]>([]);
  searchText = signal('');
  loading = signal(true);
  filteredPosts = computed(() => {
  const search = this.searchText().toLowerCase().trim();

  if (!search) {
    return this.posts();
  }

  return this.posts().filter(post =>
    post.title.toLowerCase().includes(search) ||
    post.body.toLowerCase().includes(search)
  );
});

  constructor() {
  this.loading.set(true);

  this.api.getPosts().subscribe(data => {

    setTimeout(() => {

      this.posts.set(data);

      this.loading.set(false);

    }, 3000); // 3 seconds

  });
}
  logout() {

  Swal.fire({

    title: 'Logout?',
    text: 'Are you sure you want to logout?',
    icon: 'warning',
    showCancelButton: true,
    confirmButtonColor: '#3085d6',
    cancelButtonColor: '#d33',
    confirmButtonText: 'Yes, Logout',
    cancelButtonText: 'Cancel'

  }).then((result) => {

    if (result.isConfirmed) {
      this.authService.logout();
      Swal.fire({
        title: 'Logged Out!',
        text: 'You have been logged out successfully.',
        icon: 'success',
        timer: 1500,
        showConfirmButton: false
      });

      setTimeout(() => {
        this.router.navigate(['/login']);
      }, 1500);

    }

  });

}
}