import { ModuleWithProviders } from '@angular/core/src/metadata/ng_module';
import { Routes, RouterModule } from '@angular/router';

import { UsersComponent } from './users/users.component';
import { ServicesComponent } from './services/services.component';

export const ROUTES: Routes = [
    {path: '', redirectTo: 'users', pathMatch: 'full'},
    {path: 'services', component: ServicesComponent},
    {path: 'users', component: UsersComponent},
];

export const ROUTING: ModuleWithProviders = RouterModule.forRoot(ROUTES);
