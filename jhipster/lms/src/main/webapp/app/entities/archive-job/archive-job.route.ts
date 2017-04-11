import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { PaginationUtil } from 'ng-jhipster';

import { ArchiveJobComponent } from './archive-job.component';
import { ArchiveJobDetailComponent } from './archive-job-detail.component';
import { ArchiveJobPopupComponent } from './archive-job-dialog.component';
import { ArchiveJobDeletePopupComponent } from './archive-job-delete-dialog.component';

import { Principal } from '../../shared';


export const archiveJobRoute: Routes = [
  {
    path: 'archive-job',
    component: ArchiveJobComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'lmsApp.archiveJob.home.title'
    },
    canActivate: [UserRouteAccessService]
  }, {
    path: 'archive-job/:id',
    component: ArchiveJobDetailComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'lmsApp.archiveJob.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const archiveJobPopupRoute: Routes = [
  {
    path: 'archive-job-new',
    component: ArchiveJobPopupComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'lmsApp.archiveJob.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  },
  {
    path: 'archive-job/:id/edit',
    component: ArchiveJobPopupComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'lmsApp.archiveJob.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  },
  {
    path: 'archive-job/:id/delete',
    component: ArchiveJobDeletePopupComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'lmsApp.archiveJob.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
