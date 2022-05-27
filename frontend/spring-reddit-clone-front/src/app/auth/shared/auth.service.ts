import { LoginResponse } from './../../components/auth/login/login.response.payload';
import { LoginRequestPayload } from './../../components/auth/login/login.request.payload';
import { SignupRequestPayload } from './../../components/auth/signup/signup-request.payload';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { LocalStorageService } from 'ngx-webstorage';
import { map, tap } from 'rxjs/operators';


@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient, private localStorage: LocalStorageService) { }

  baseUrl: string ="http://localhost:8080/api/auth";

  signUp(signupInfo: SignupRequestPayload): Observable<any>{
    return this.http.post(`${this.baseUrl}/signup`,signupInfo, { responseType: 'text'});
  }

  login(loginRequestPayload:LoginRequestPayload): Observable<boolean>{
    return this.http.post<LoginResponse>(`${this.baseUrl}/login`,
    loginRequestPayload).pipe(map(data => { 
      this.localStorage.store('authenticationToken', data.authenticationToken);
      this.localStorage.store('username', data.username);
      this.localStorage.store('refreshToken', data.refreshToken);
      this.localStorage.store('expiresAt', data.expiresAt);

      return true;
    }))
  }

  getJwtToken(){
    return this.localStorage.retrieve('authenticationToken');
  }

  refreshToken(){
    const refreshTokenPayload = {
      refreshToken: this.getRefreshToken(),
      username: this.getUsername()
    }
    return this.http.post<LoginResponse>(`${this.baseUrl}/refresh/token`,
    refreshTokenPayload)
    .pipe(tap(response => {
      this.localStorage.store('expiresAt', response.expiresAt);
    }))
  }

  getUsername(){
    this.localStorage.retrieve('username');
  }
  
  getRefreshToken(){
    return this.localStorage.retrieve('refreshToken');
  }
}
