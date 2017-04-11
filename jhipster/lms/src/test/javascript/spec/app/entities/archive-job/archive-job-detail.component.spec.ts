import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { DateUtils, DataUtils, EventManager } from 'ng-jhipster';
import { LmsTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { ArchiveJobDetailComponent } from '../../../../../../main/webapp/app/entities/archive-job/archive-job-detail.component';
import { ArchiveJobService } from '../../../../../../main/webapp/app/entities/archive-job/archive-job.service';
import { ArchiveJob } from '../../../../../../main/webapp/app/entities/archive-job/archive-job.model';

describe('Component Tests', () => {

    describe('ArchiveJob Management Detail Component', () => {
        let comp: ArchiveJobDetailComponent;
        let fixture: ComponentFixture<ArchiveJobDetailComponent>;
        let service: ArchiveJobService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [LmsTestModule],
                declarations: [ArchiveJobDetailComponent],
                providers: [
                    DateUtils,
                    DataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    ArchiveJobService,
                    EventManager
                ]
            }).overrideComponent(ArchiveJobDetailComponent, {
                set: {
                    template: ''
                }
            }).compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(ArchiveJobDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ArchiveJobService);
        });


        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new ArchiveJob(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.archiveJob).toEqual(jasmine.objectContaining({id:10}));
            });
        });
    });

});
