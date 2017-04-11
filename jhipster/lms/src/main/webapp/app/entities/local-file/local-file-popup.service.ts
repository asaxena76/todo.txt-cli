import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { LocalFile } from './local-file.model';
import { LocalFileService } from './local-file.service';
@Injectable()
export class LocalFilePopupService {
    private isOpen = false;
    constructor (
        private modalService: NgbModal,
        private router: Router,
        private localFileService: LocalFileService

    ) {}

    open (component: Component, id?: number | any): NgbModalRef {
        if (this.isOpen) {
            return;
        }
        this.isOpen = true;

        if (id) {
            this.localFileService.find(id).subscribe(localFile => {
                if (localFile.dateAdded) {
                    localFile.dateAdded = {
                        year: localFile.dateAdded.getFullYear(),
                        month: localFile.dateAdded.getMonth() + 1,
                        day: localFile.dateAdded.getDate()
                    };
                }
                this.localFileModalRef(component, localFile);
            });
        } else {
            return this.localFileModalRef(component, new LocalFile());
        }
    }

    localFileModalRef(component: Component, localFile: LocalFile): NgbModalRef {
        let modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.localFile = localFile;
        modalRef.result.then(result => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true });
            this.isOpen = false;
        }, (reason) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true });
            this.isOpen = false;
        });
        return modalRef;
    }
}
