import { HttpInterceptorFn } from '@angular/common/http';
import { HttpRequest, HttpHandlerFn } from '@angular/common/http';
import { environment } from '../../environments/environment.development';

export const jwtInterceptor: HttpInterceptorFn = (req: HttpRequest<any>, next: HttpHandlerFn) => {
	const excludedUrls = [`${environment.apiUrl}/auth/signup`, `${environment.apiUrl}/auth/login`];

  if (excludedUrls.some(url => req.url.includes(url))) {
    return next(req);
  }

  const token = localStorage.getItem('jwtToken');

  if (token) {
    req = req.clone({
      setHeaders: {
        Authorization: `Bearer ${token}`
      }
    });
  }

  return next(req);
};