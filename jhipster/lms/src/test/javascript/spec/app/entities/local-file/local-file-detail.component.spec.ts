import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { DateUtils, DataUtils, EventManager } from 'ng-jhipster';
import { LmsTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { LocalFileDetailComponent } from '../../../../../../main/webapp/app/entities/local-file/local-file-detail.component';
import { LocalFileService } from '../../../../../../main/webapp/app/entities/local-file/local-file.service';
import { LocalFile } from '../../../../../../main/webapp/app/entities/local-file/local-file.model';

describe('Component Tests', () => {

    describe('LocalFile Management Detail Component', () => {
        let comp: LocalFileDetailComponent;
        let fixture: ComponentFixture<LocalFileDetailComponent>;
        let service: LocalFileService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [LmsTestModule],
                declarations: [LocalFileDetailComponent],
                providers: [
                    DateUtils,
                    DataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    LocalFileService,
                    EventManager
                ]
            }).overrideComponent(LocalFileDetailComponent, {
                set: {
                    template: ''
                }
            }).compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(LocalFileDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(LocalFileService);
        });


        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new LocalFile(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.localFile).toEqual(jasmine.objectContaining({id:10}));
            });
        });
    });

});
