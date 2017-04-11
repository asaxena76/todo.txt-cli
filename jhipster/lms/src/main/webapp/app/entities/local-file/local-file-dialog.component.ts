import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EventManager, AlertService, JhiLanguageService } from 'ng-jhipster';

import { LocalFile } from './local-file.model';
import { LocalFilePopupService } from './local-file-popup.service';
import { LocalFileService } from './local-file.service';

@Component({
    selector: 'jhi-local-file-dialog',
    templateUrl: './local-file-dialog.component.html'
})
export class LocalFileDialogComponent implements OnInit {

    localFile: LocalFile;
    authorities: any[];
    isSaving: boolean;
    constructor(
        public activeModal: NgbActiveModal,
        private jhiLanguageService: JhiLanguageService,
        private alertService: AlertService,
        private localFileService: LocalFileService,
        private eventManager: EventManager
    ) {
        this.jhiLanguageService.setLocations(['localFile', 'fileType']);
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
        if (this.localFile.id !== undefined) {
            this.localFileService.update(this.localFile)
                .subscribe((res: LocalFile) =>
                    this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
        } else {
            this.localFileService.create(this.localFile)
                .subscribe((res: LocalFile) =>
                    this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
        }
    }

    private onSaveSuccess (result: LocalFile) {
        this.eventManager.broadcast({ name: 'localFileListModification', content: 'OK'});
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
    selector: 'jhi-local-file-popup',
    template: ''
})
export class LocalFilePopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor (
        private route: ActivatedRoute,
        private localFilePopupService: LocalFilePopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe(params => {
            if ( params['id'] ) {
                this.modalRef = this.localFilePopupService
                    .open(LocalFileDialogComponent, params['id']);
            } else {
                this.modalRef = this.localFilePopupService
                    .open(LocalFileDialogComponent);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
