import { LoginResponse } from './../components/auth/login/login.response.payload';
import { AuthService } from './shared/auth.service';
import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor,
  HttpErrorResponse
} from '@angular/common/http';
import { BehaviorSubject, Observable, throwError } from 'rxjs';
import { catchError, switchMap } from 'rxjs/operators';

@Injectable()
export class InterceptorInterceptor implements HttpInterceptor {

  isTokenRefreshing = false;
  refreshTokenSubject: BehaviorSubject<any> = new BehaviorSubject(null)
  constructor(public authService: AuthService) {}

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const jwtToken = this.authService.getJwtToken();
    if (jwtToken) {
      this.addToken(request, jwtToken);
    }
    return next.handle(request).pipe(catchError(error => {
      if (error instanceof HttpErrorResponse && error.status === 403) {
        return this.handleAuthErrors(request,next);
      } else {
        return throwError(error);
      }
    }));
  }

  private handleAuthErrors(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    if(!this.isTokenRefreshing){
      this.isTokenRefreshing = true;
      this.refreshTokenSubject.next(null);
      return this.authService.refreshToken().pipe(
        switchMap((refreshTokenResponse: LoginResponse) => {
          this.isTokenRefreshing = false;
          this.refreshTokenSubject
          .next(refreshTokenResponse.authenticationToken);
          return next.handle(this.addToken(request,refreshTokenResponse.authenticationToken));
        })
      )
    }else {
      return next.handle(request).pipe(catchError(error => throwError(error)));
    }
  }

  addToken(request: HttpRequest<any>, jwtToken: any) {
    return request.clone({
      headers: request.headers.set('Authorization', 'Bearer ' + jwtToken)
    })
  }
}
