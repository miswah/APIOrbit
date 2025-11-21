import { Routes } from '@angular/router';
import { AppLayoutMock } from './app/layout/component/app.layout';
import { Dashboard } from './app/pages/dashboard/dashboard';
import { Documentation } from './app/pages/documentation/documentation';
import { Landing } from './app/pages/landing/landing';
import { Notfound } from './app/pages/notfound/notfound';
import { AppLayout } from '@/main/layout/component/app.layout';
import { AuthGuard } from '@/auth/helpers/auth.guard';
import { LoginComponent } from '@/auth/components/login/login.component';
import { SignupComponent } from '@/auth/components/signup/signup.component';

export const appRoutes: Routes = [
    {
        path: '',
        component: AppLayout,
        canActivate: [AuthGuard],
        children: [
            { path: '', loadComponent: () => import('./app/main/ui/dashboard/dashboard.component').then(m => m.DashboardComponent) },
            { path: 'users', loadComponent: () => import('./app/main/ui/users/users.component').then(m => m.UsersComponent) },
            { path : 'apis', loadComponent: () => import('./app/main/ui/apis/apis.component').then(m => m.ApisComponent)}
        ]
    },
    { path: 'login', component: LoginComponent },
    { path: 'signup', component: SignupComponent},
    {
        path: 'mock',
        component: AppLayoutMock,
        children: [
            { path: '', component: Dashboard },
            { path: 'uikit', loadChildren: () => import('./app/pages/uikit/uikit.routes') },
            { path: 'documentation', component: Documentation },
            { path: 'pages', loadChildren: () => import('./app/pages/pages.routes') }
        ]
    },
    { path: 'landing', component: Landing },
    { path: 'notfound', component: Notfound },
    { path: 'auth', loadChildren: () => import('./app/pages/auth/auth.routes') },
    { path: '**', redirectTo: '/notfound' }
];
