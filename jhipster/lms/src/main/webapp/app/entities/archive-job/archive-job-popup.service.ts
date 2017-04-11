import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { DatePipe } from '@angular/common';
import { ArchiveJob } from './archive-job.model';
import { ArchiveJobService } from './archive-job.service';
@Injectable()
export class ArchiveJobPopupService {
    private isOpen = false;
    constructor (
        private datePipe: DatePipe,
        private modalService: NgbModal,
        private router: Router,
        private archiveJobService: ArchiveJobService

    ) {}

    open (component: Component, id?: number | any): NgbModalRef {
        if (this.isOpen) {
            return;
        }
        this.isOpen = true;

        if (id) {
            this.archiveJobService.find(id).subscribe(archiveJob => {
                archiveJob.startTime = this.datePipe
                    .transform(archiveJob.startTime, 'yyyy-MM-ddThh:mm');
                archiveJob.endTime = this.datePipe
                    .transform(archiveJob.endTime, 'yyyy-MM-ddThh:mm');
                this.archiveJobModalRef(component, archiveJob);
            });
        } else {
            return this.archiveJobModalRef(component, new ArchiveJob());
        }
    }

    archiveJobModalRef(component: Component, archiveJob: ArchiveJob): NgbModalRef {
        let modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.archiveJob = archiveJob;
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
