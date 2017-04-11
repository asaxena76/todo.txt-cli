import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EventManager, AlertService, JhiLanguageService } from 'ng-jhipster';

import { ArchiveJob } from './archive-job.model';
import { ArchiveJobPopupService } from './archive-job-popup.service';
import { ArchiveJobService } from './archive-job.service';

@Component({
    selector: 'jhi-archive-job-dialog',
    templateUrl: './archive-job-dialog.component.html'
})
export class ArchiveJobDialogComponent implements OnInit {

    archiveJob: ArchiveJob;
    authorities: any[];
    isSaving: boolean;
    constructor(
        public activeModal: NgbActiveModal,
        private jhiLanguageService: JhiLanguageService,
        private alertService: AlertService,
        private archiveJobService: ArchiveJobService,
        private eventManager: EventManager
    ) {
        this.jhiLanguageService.setLocations(['archiveJob', 'archiveJobStatus']);
    }

    ngOnInit() {
        this.isSaving = false;
        this.authorities = ['ROLE_USER', 'ROLE_ADMIN'];
    }
    clear () {
        this.activeModal.dismiss('cancel');
    }

    save () {
        this.isSaving = true;
        if (this.archiveJob.id !== undefined) {
            this.archiveJobService.update(this.archiveJob)
                .subscribe((res: ArchiveJob) =>
                    this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
        } else {
            this.archiveJobService.create(this.archiveJob)
                .subscribe((res: ArchiveJob) =>
                    this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
        }
    }

    private onSaveSuccess (result: ArchiveJob) {
        this.eventManager.broadcast({ name: 'archiveJobListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError (error) {
        try {
            error.json();
        } catch (exception) {
            error.message = error.text();
        }
        this.isSaving = false;
        this.onError(error);
    }

    private onError (error) {
        this.alertService.error(error.message, null, null);
    }
}

@Component({
    selector: 'jhi-archive-job-popup',
    template: ''
})
export class ArchiveJobPopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor (
        private route: ActivatedRoute,
        private archiveJobPopupService: ArchiveJobPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe(params => {
            if ( params['id'] ) {
                this.modalRef = this.archiveJobPopupService
                    .open(ArchiveJobDialogComponent, params['id']);
            } else {
                this.modalRef = this.archiveJobPopupService
                    .open(ArchiveJobDialogComponent);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
