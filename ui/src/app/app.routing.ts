import { ModuleWithProviders } from '@angular/core/src/metadata/ng_module';
import { Routes, RouterModule } from '@angular/router';

export const ROUTES: Routes = [
    {path: '', redirectTo: 'users', pathMatch: 'full'},
    {path: 'banking', loadChildren: 'app/banking/banking.module#BankingModule'},
    {path: 'payroll', loadChildren: 'app/payroll/payroll.module#PayrollModule'},
    {path: 'program', loadChildren: 'app/program/program.module#ProgramModule'},
    {path: 'services', loadChildren: 'app/services/services.module#ServicesModule'},
    {path: 'users', loadChildren: 'app/users/users.module#UsersModule'},
    // {path: 'program', component: ProgramComponent}, 
    // {path: 'services/billing', component: BillingRulesComponent},
];

export const ROUTING: ModuleWithProviders = RouterModule.forRoot(ROUTES);
