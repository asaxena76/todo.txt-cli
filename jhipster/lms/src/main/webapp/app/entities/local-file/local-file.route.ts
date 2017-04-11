import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { PaginationUtil } from 'ng-jhipster';

import { LocalFileComponent } from './local-file.component';
import { LocalFileDetailComponent } from './local-file-detail.component';
import { LocalFilePopupComponent } from './local-file-dialog.component';
import { LocalFileDeletePopupComponent } from './local-file-delete-dialog.component';

import { Principal } from '../../shared';


export const localFileRoute: Routes = [
  {
    path: 'local-file',
    component: LocalFileComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'lmsApp.localFile.home.title'
    },
    canActivate: [UserRouteAccessService]
  }, {
    path: 'local-file/:id',
    component: LocalFileDetailComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'lmsApp.localFile.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const localFilePopupRoute: Routes = [
  {
    path: 'local-file-new',
    component: LocalFilePopupComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'lmsApp.localFile.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  },
  {
    path: 'local-file/:id/edit',
    component: LocalFilePopupComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'lmsApp.localFile.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  },
  {
    path: 'local-file/:id/delete',
    component: LocalFileDeletePopupComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'lmsApp.localFile.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
