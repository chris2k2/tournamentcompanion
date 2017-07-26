webpackJsonp([1,4],{

/***/ 332:
/***/ (function(module, exports) {

function webpackEmptyContext(req) {
	throw new Error("Cannot find module '" + req + "'.");
}
webpackEmptyContext.keys = function() { return []; };
webpackEmptyContext.resolve = webpackEmptyContext;
module.exports = webpackEmptyContext;
webpackEmptyContext.id = 332;


/***/ }),

/***/ 333:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
Object.defineProperty(__webpack_exports__, "__esModule", { value: true });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_platform_browser_dynamic__ = __webpack_require__(420);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__app_app_module__ = __webpack_require__(451);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__environments_environment__ = __webpack_require__(456);




if (__WEBPACK_IMPORTED_MODULE_3__environments_environment__["a" /* environment */].production) {
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["a" /* enableProdMode */])();
}
__webpack_require__.i(__WEBPACK_IMPORTED_MODULE_1__angular_platform_browser_dynamic__["a" /* platformBrowserDynamic */])().bootstrapModule(__WEBPACK_IMPORTED_MODULE_2__app_app_module__["a" /* AppModule */]);
//# sourceMappingURL=main.js.map

/***/ }),

/***/ 450:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__backend_service__ = __webpack_require__(83);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_core__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__angular_router__ = __webpack_require__(187);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return AppComponent; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};



var AppComponent = (function () {
    function AppComponent(beService, router) {
        this.beService = beService;
        this.router = router;
        this.isCollapsed = true;
    }
    AppComponent.prototype.toggleCollapse = function () {
        this.isCollapsed = !this.isCollapsed;
    };
    AppComponent.prototype.ngOnInit = function () {
        var _this = this;
        this.disciplines = JSON.parse('{ "idNames":[] }');
        this.beService.getDisciplines().subscribe(function (cs) { return _this.disciplines = cs; }, function (error) { return console.log(error); });
    };
    AppComponent.prototype.changeRoute = function (url) {
        var _this = this;
        this.router.navigateByUrl('/welcome', { skipLocationChange: true });
        var fullUrl = 'groups/' + url;
        setTimeout(function () { return _this.router.navigate([fullUrl]); });
    };
    AppComponent = __decorate([
        __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_1__angular_core__["_6" /* Component */])({
            selector: 'app-root',
            template: __webpack_require__(512)
        }), 
        __metadata('design:paramtypes', [(typeof (_a = typeof __WEBPACK_IMPORTED_MODULE_0__backend_service__["a" /* BackendService */] !== 'undefined' && __WEBPACK_IMPORTED_MODULE_0__backend_service__["a" /* BackendService */]) === 'function' && _a) || Object, (typeof (_b = typeof __WEBPACK_IMPORTED_MODULE_2__angular_router__["c" /* Router */] !== 'undefined' && __WEBPACK_IMPORTED_MODULE_2__angular_router__["c" /* Router */]) === 'function' && _b) || Object])
    ], AppComponent);
    return AppComponent;
    var _a, _b;
}());
//# sourceMappingURL=app.component.js.map

/***/ }),

/***/ 451:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_platform_browser__ = __webpack_require__(127);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_core__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__angular_forms__ = __webpack_require__(411);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__angular_http__ = __webpack_require__(282);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__app_component__ = __webpack_require__(450);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_5__backend_service__ = __webpack_require__(83);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_6__welcome_welcome_component__ = __webpack_require__(455);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_7__clubstandings_clubstandings_component__ = __webpack_require__(452);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_8__groups_groups_component__ = __webpack_require__(453);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_9__matches_matches_component__ = __webpack_require__(454);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_10__angular_router__ = __webpack_require__(187);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return AppModule; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};











var AppModule = (function () {
    function AppModule() {
    }
    AppModule = __decorate([
        __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_1__angular_core__["b" /* NgModule */])({
            declarations: [
                __WEBPACK_IMPORTED_MODULE_4__app_component__["a" /* AppComponent */],
                __WEBPACK_IMPORTED_MODULE_6__welcome_welcome_component__["a" /* WelcomeComponent */],
                __WEBPACK_IMPORTED_MODULE_8__groups_groups_component__["a" /* GroupsComponent */],
                __WEBPACK_IMPORTED_MODULE_7__clubstandings_clubstandings_component__["a" /* ClubstandingsComponent */],
                __WEBPACK_IMPORTED_MODULE_9__matches_matches_component__["a" /* MatchesComponent */]
            ],
            imports: [
                __WEBPACK_IMPORTED_MODULE_0__angular_platform_browser__["a" /* BrowserModule */],
                __WEBPACK_IMPORTED_MODULE_2__angular_forms__["a" /* FormsModule */],
                __WEBPACK_IMPORTED_MODULE_3__angular_http__["a" /* HttpModule */],
                __WEBPACK_IMPORTED_MODULE_10__angular_router__["a" /* RouterModule */].forRoot([
                    { path: 'clubs', component: __WEBPACK_IMPORTED_MODULE_7__clubstandings_clubstandings_component__["a" /* ClubstandingsComponent */] },
                    { path: 'matches', component: __WEBPACK_IMPORTED_MODULE_9__matches_matches_component__["a" /* MatchesComponent */] },
                    { path: 'groups/:id', component: __WEBPACK_IMPORTED_MODULE_8__groups_groups_component__["a" /* GroupsComponent */] },
                    { path: 'welcome', component: __WEBPACK_IMPORTED_MODULE_6__welcome_welcome_component__["a" /* WelcomeComponent */] },
                    { path: '', redirectTo: 'welcome', pathMatch: 'full' },
                    { path: '**', component: __WEBPACK_IMPORTED_MODULE_6__welcome_welcome_component__["a" /* WelcomeComponent */] }
                ])],
            providers: [__WEBPACK_IMPORTED_MODULE_5__backend_service__["a" /* BackendService */]],
            bootstrap: [__WEBPACK_IMPORTED_MODULE_4__app_component__["a" /* AppComponent */]]
        }), 
        __metadata('design:paramtypes', [])
    ], AppModule);
    return AppModule;
}());
//# sourceMappingURL=app.module.js.map

/***/ }),

/***/ 452:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__backend_service__ = __webpack_require__(83);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_core__ = __webpack_require__(0);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return ClubstandingsComponent; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};


var ClubstandingsComponent = (function () {
    function ClubstandingsComponent(beService) {
        this.beService = beService;
    }
    ClubstandingsComponent.prototype.ngOnInit = function () {
        var _this = this;
        this.standings = JSON.parse('{ "standings":[] }');
        this.beService.getClubStandings().subscribe(function (cs) { return _this.standings = cs; }, function (error) { return console.log(error); });
    };
    ClubstandingsComponent = __decorate([
        __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_1__angular_core__["_6" /* Component */])({
            selector: 'app-clubstandings',
            template: __webpack_require__(513),
        }), 
        __metadata('design:paramtypes', [(typeof (_a = typeof __WEBPACK_IMPORTED_MODULE_0__backend_service__["a" /* BackendService */] !== 'undefined' && __WEBPACK_IMPORTED_MODULE_0__backend_service__["a" /* BackendService */]) === 'function' && _a) || Object])
    ], ClubstandingsComponent);
    return ClubstandingsComponent;
    var _a;
}());
//# sourceMappingURL=clubstandings.component.js.map

/***/ }),

/***/ 453:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__backend_service__ = __webpack_require__(83);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_core__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__angular_router__ = __webpack_require__(187);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return GroupsComponent; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};



var GroupsComponent = (function () {
    function GroupsComponent(beService, route) {
        this.beService = beService;
        this.route = route;
    }
    GroupsComponent.prototype.ngOnInit = function () {
        var _this = this;
        this.disc = JSON.parse('{ "groups":[] }');
        this.beService.getDiscipline(this.route.snapshot.params['id']).subscribe(function (cs) { return _this.disc = cs; }, function (error) { return console.log(error); });
    };
    GroupsComponent.prototype.getIt = function (id) {
        return '#' + id;
    };
    GroupsComponent = __decorate([
        __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_1__angular_core__["_6" /* Component */])({
            selector: 'app-groups',
            template: __webpack_require__(514),
            styles: [__webpack_require__(510)]
        }), 
        __metadata('design:paramtypes', [(typeof (_a = typeof __WEBPACK_IMPORTED_MODULE_0__backend_service__["a" /* BackendService */] !== 'undefined' && __WEBPACK_IMPORTED_MODULE_0__backend_service__["a" /* BackendService */]) === 'function' && _a) || Object, (typeof (_b = typeof __WEBPACK_IMPORTED_MODULE_2__angular_router__["b" /* ActivatedRoute */] !== 'undefined' && __WEBPACK_IMPORTED_MODULE_2__angular_router__["b" /* ActivatedRoute */]) === 'function' && _b) || Object])
    ], GroupsComponent);
    return GroupsComponent;
    var _a, _b;
}());
//# sourceMappingURL=groups.component.js.map

/***/ }),

/***/ 454:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__backend_service__ = __webpack_require__(83);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_core__ = __webpack_require__(0);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return MatchesComponent; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};


var MatchesComponent = (function () {
    function MatchesComponent(beService) {
        this.beService = beService;
    }
    MatchesComponent.prototype.ngOnInit = function () {
        var _this = this;
        this.matches = JSON.parse('{ "matches":[] }');
        this.beService.getMatches().subscribe(function (cs) { return _this.matches = cs; }, function (error) { return console.log(error); });
    };
    MatchesComponent = __decorate([
        __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_1__angular_core__["_6" /* Component */])({
            selector: 'app-matches',
            template: __webpack_require__(515),
        }), 
        __metadata('design:paramtypes', [(typeof (_a = typeof __WEBPACK_IMPORTED_MODULE_0__backend_service__["a" /* BackendService */] !== 'undefined' && __WEBPACK_IMPORTED_MODULE_0__backend_service__["a" /* BackendService */]) === 'function' && _a) || Object])
    ], MatchesComponent);
    return MatchesComponent;
    var _a;
}());
//# sourceMappingURL=matches.component.js.map

/***/ }),

/***/ 455:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(0);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return WelcomeComponent; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};

var WelcomeComponent = (function () {
    function WelcomeComponent() {
    }
    WelcomeComponent = __decorate([
        __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["_6" /* Component */])({
            selector: 'app-welcome',
            template: __webpack_require__(516)
        }), 
        __metadata('design:paramtypes', [])
    ], WelcomeComponent);
    return WelcomeComponent;
}());
//# sourceMappingURL=welcome.component.js.map

/***/ }),

/***/ 456:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return environment; });
// The file contents for the current environment will overwrite these during build.
// The build system defaults to the dev environment which uses `environment.ts`, but if you do
// `ng build --env=prod` then `environment.prod.ts` will be used instead.
// The list of which env maps to which file can be found in `.angular-cli.json`.
var environment = {
    production: false
};
//# sourceMappingURL=environment.js.map

/***/ }),

/***/ 510:
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(207)();
// imports


// module
exports.push([module.i, "", ""]);

// exports


/*** EXPORTS FROM exports-loader ***/
module.exports = module.exports.toString();

/***/ }),

/***/ 512:
/***/ (function(module, exports) {

module.exports = "\r\n<nav class=\"navbar navbar-inverse\" data-spy=\"affix\">\r\n\t<div class=\"container-fluid\">\r\n\t\t<div class=\"navbar-header\">\r\n\t\t\t<button type=\"button\" class=\"navbar-toggle collapsed\"\r\n\t\t\t\t(click)=\"toggleCollapse()\" aria-expanded=\"false\">\r\n\t\t\t\t<span class=\"icon-bar\"></span> <span class=\"icon-bar\"></span> <span\r\n\t\t\t\t\tclass=\"icon-bar\"></span>\r\n\t\t\t</button>\r\n\t\t\t<a class=\"navbar-brand\" routerLink=\"/welcome\"\r\n\t\t\t\t(click)=\"toggleCollapse()\" aria-expanded=\"false\">Zirndorfer SMS</a>\r\n\t\t</div>\r\n\t\t<div class=\"navbar-collapse\" id=\"myNavbar\"\r\n\t\t\t[class.collapse]=\"isCollapsed\">\r\n\t\t\t<ul class=\"nav navbar-nav\">\r\n\t\t\t\t<li class=\"dropdown\"><a class=\"dropdown-toggle\"\r\n\t\t\t\t\tdata-toggle=\"dropdown\" href=\"\">Disziplinen<span class=\"caret\"></span></a>\r\n\t\t\t\t\t<ul class=\"dropdown-menu\" (click)=\"toggleCollapse()\"\r\n\t\t\t\t\t\taria-expanded=\"false\">\r\n\t\t\t\t\t\t<li *ngFor='let x of disciplines.idNames'><a class='kinda-link' (click)=\"changeRoute(x.id)\">{{x.name}}</a></li>\r\n\t\t\t\t\t</ul></li>\r\n\t\t\t\t<li><a routerLink=\"/matches\" (click)=\"toggleCollapse()\"\r\n\t\t\t\t\taria-expanded=\"false\">Spielplan</a></li>\r\n\t\t\t\t<li><a routerLink=\"/clubs\" (click)=\"toggleCollapse()\"\r\n\t\t\t\t\taria-expanded=\"false\">Vereinswertung</a></li>\r\n\t\t\t</ul>\r\n\t\t</div>\r\n\t</div>\r\n</nav>\r\n\r\n<div class=\"container-fluid\">\r\n\t<router-outlet></router-outlet>\r\n</div>"

/***/ }),

/***/ 513:
/***/ (function(module, exports) {

module.exports = "\r\n<table class=\"table table-striped\">\r\n\t<tr>\r\n\t\t<th>#</th>\r\n\t\t<th>Verein</th>\r\n\t\t<th>Punkte</th>\r\n\t</tr>\r\n\r\n\t<tr *ngFor='let x of standings.standings'>\r\n\t\t<td>{{x.position}}.</td>\r\n\t\t<td>{{x.clubName}}</td>\r\n\t\t<td>{{x.points}}</td>\r\n\t</tr>\r\n</table>\r\n\r\n<p>Es werden nur Vereine angezeigt, die bereits Punkte haben. Der\r\n\tBerechnung liegt folgende Punkteverteilung zu Grunde: </p>\r\n\r\n<table class=\"table\">\r\n\t<tr>\r\n\t\t<td>1.Platz</td>\r\n\t\t<td>10</td>\r\n\t</tr>\r\n\t<tr>\r\n\t\t<td>2.Platz</td>\r\n\t\t<td>6</td>\r\n\t</tr>\r\n\t<tr>\r\n\t\t<td>3.Platz</td>\r\n\t\t<td>4</td>\r\n\t</tr>\r\n\t<tr>\r\n\t\t<td>4.Platz</td>\r\n\t\t<td>2</td>\r\n\t</tr>\r\n\t<tr>\r\n\t\t<td>Sieger Trostrunde</td>\r\n\t\t<td>1</td>\r\n\t</tr>\r\n</table>\r\n\r\n<p>Die Punkte werden anteilig vergeben, wenn beide Spieler nicht für\r\n\tden selben Verein antreten.</p>\r\n<p>\r\n\t<b>Bei Gleichstand entscheidet die Turnierleitung!</b>\r\n</p>"

/***/ }),

/***/ 514:
/***/ (function(module, exports) {

module.exports = "\r\n<article>\r\n\t<section *ngFor='let group of disc.groups'>\r\n\t\t<div *ngIf='!group.ko'>\r\n\t\t\t<section>\r\n\t\t\t\t<h2>{{group.name}}</h2>\r\n\t\t\t\t<table class=\"table table-striped\" *ngIf='!group.ko'>\r\n\t\t\t\t\t<tr>\r\n\t\t\t\t\t\t<th>#</th>\r\n\t\t\t\t\t\t<th>Team</th>\r\n\t\t\t\t\t\t<th>Spiele</th>\r\n\t\t\t\t\t\t<th>Sätze</th>\r\n\t\t\t\t\t\t<th>Punkte</th>\r\n\t\t\t\t\t</tr>\r\n\t\t\t\t\t<tr *ngFor='let standing of group.standings'>\r\n\t\t\t\t\t\t<td>{{standing.ranking}}.</td>\r\n\t\t\t\t\t\t<td>{{standing.team.teamname}}</td>\r\n\t\t\t\t\t\t<td>{{standing.matchesFor}}:{{standing.matchesAgainst}}</td>\r\n\t\t\t\t\t\t<td>{{standing.setsFor}}:{{standing.setsAgainst}}</td>\r\n\t\t\t\t\t\t<td>{{standing.pointsFor}}:{{standing.pointsAgainst}}</td>\r\n\t\t\t\t\t</tr>\r\n\t\t\t\t</table>\r\n\t\t\t</section>\r\n\t\t\t<a class=\"btn collapsed\" data-toggle=\"collapse\"\r\n\t\t\t\t[attr.data-target]=\"'#games' + group.id\" aria-expanded=\"true\">&raquo;\r\n\t\t\t\tAlle Spiele </a>\r\n\t\t\t<section id='games{{group.id}}' class=\"collapse\"\r\n\t\t\t\taria-expanded=\"false\">\r\n\t\t\t\t<table class=\"table table-striped\">\r\n\t\t\t\t\t<tr>\r\n\t\t\t\t\t\t<th>Spiel</th>\r\n\t\t\t\t\t\t<th>Ergebnis</th>\r\n\t\t\t\t\t</tr>\r\n\t\t\t\t\t<tr *ngFor='let m of group.matches'>\r\n\t\t\t\t\t\t<td>{{m.matchString}}</td>\r\n\t\t\t\t\t\t<td>{{m.result}}</td>\r\n\t\t\t\t\t</tr>\r\n\t\t\t\t</table>\r\n\t\t\t</section>\r\n\t\t</div>\r\n\t\t<div *ngIf='group.ko'>\r\n\t\t\t<h2>{{group.name}}</h2>\r\n\t\t\t<a class=\"btn collapsed\" data-toggle=\"collapse\"\r\n\t\t\t\t[attr.data-target]=\"'#games' + group.id\" aria-expanded=\"true\">&raquo;\r\n\t\t\t\tAlle Spiele </a>\r\n\t\t\t<section id='games{{group.id}}' class=\"collapse\"\r\n\t\t\t\taria-expanded=\"false\">\r\n\t\t\t\t<div *ngFor='let koround of group.koRounds'>\r\n\t\t\t\t\t<h3>{{koround.name}}</h3>\r\n\t\t\t\t\t<table class=\"table table-striped\">\r\n\t\t\t\t\t\t<tr>\r\n\t\t\t\t\t\t\t<th>Spiel</th>\r\n\t\t\t\t\t\t\t<th>Ergebnis</th>\r\n\t\t\t\t\t\t</tr>\r\n\t\t\t\t\t\t<tr *ngFor='let m of koround.matches'>\r\n\t\t\t\t\t\t\t<td>{{m.matchString}}</td>\r\n\t\t\t\t\t\t\t<td>{{m.result}}</td>\r\n\t\t\t\t\t\t</tr>\r\n\t\t\t\t\t</table>\r\n\t\t\t\t</div>\r\n\t\t\t</section>\r\n\t\t</div>\r\n\t</section>\r\n</article>"

/***/ }),

/***/ 515:
/***/ (function(module, exports) {

module.exports = "<section id=\"allgames\">\r\n\t<table class=\"table table-striped\">\r\n\t\t<tr>\r\n\t\t\t<th>Uhrzeit</th>\r\n\t\t\t<th>Spiel</th>\r\n\t\t\t<th>Ergebnis</th>\r\n\t\t</tr>\r\n\t\t<tr *ngFor='let m of matches.matches'>\r\n\t\t\t<td>{{m.date}}</td>\r\n\t\t\t<td>{{m.matchString}}</td>\r\n\t\t\t<td>{{m.result}}</td>\r\n\t\t</tr>\r\n\t</table>\r\n</section>"

/***/ }),

/***/ 516:
/***/ (function(module, exports) {

module.exports = "<h1>Willkommen bei der Zirndorfer SMS!</h1>\r\n<p>\r\n\tHerzlich willkommen bei der diesjährigen Zirndorfer SMS. Wir freuen uns\r\n\tdich dabei zu haben. Ein Hinweis noch, diese App befindet sich noch im\r\n\tBeta Status. Bitte habe also Verständnis, wenn nicht alles direkt\r\n\tfunktioniert. Falls es Probleme gibt, du Verbesserungsvorschläge hast\r\n\toder du uns einfach für das tolle Turnier loben willst :) wende dich\r\n\tbitte an <a href=\"mailto:zirndorf_rocks@cweyermann.de\">zirndorf_rocks@cweyermann.de</a>\r\n</p>\r\n<p>\r\n\t<b>Es gilt immer der Stand der Turnierleitung! Die App ist rein\r\n\t\tinformativ!</b>\r\n</p>\r\n<p>\r\n\tAlle weiteren Informationen und die Ausschreibung findet ihr auf <a\r\n\t\thref=\"https://www.tsv-zirndorf.de/cms/index.php?id=1332\">www.tsv-zirndorf.de/cms/index.php?id=1332</a>\r\n</p>\r\n<p>Viel Spaß!</p>\r\n\r\n"

/***/ }),

/***/ 538:
/***/ (function(module, exports, __webpack_require__) {

module.exports = __webpack_require__(333);


/***/ }),

/***/ 83:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_http__ = __webpack_require__(282);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2_rxjs_Observable__ = __webpack_require__(7);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2_rxjs_Observable___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_2_rxjs_Observable__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3_rxjs_add_operator_catch__ = __webpack_require__(520);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3_rxjs_add_operator_catch___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_3_rxjs_add_operator_catch__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4_rxjs_add_operator_map__ = __webpack_require__(522);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4_rxjs_add_operator_map___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_4_rxjs_add_operator_map__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_5_rxjs_add_operator_do__ = __webpack_require__(521);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_5_rxjs_add_operator_do___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_5_rxjs_add_operator_do__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return BackendService; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};






var BackendService = (function () {
    function BackendService(http) {
        this.http = http;
        this.url = 'http://192.168.2.108:8082/btc/'; // URL to web API
    }
    BackendService.prototype.getClubStandings = function () {
        return this.http.get(this.url + 'clubs/')
            .map(function (response) { return response.json(); })
            .do(function (response) { return console.log(JSON.stringify(response)); })
            .catch(this.handleError);
    };
    BackendService.prototype.getDisciplines = function () {
        return this.http.get(this.url + 'disciplines/')
            .map(function (response) { return response.json(); })
            .do(function (response) { return console.log(JSON.stringify(response)); })
            .catch(this.handleError);
    };
    BackendService.prototype.getMatches = function () {
        return this.http.get(this.url + 'matches/')
            .map(function (response) { return response.json(); })
            .do(function (response) { return console.log(JSON.stringify(response)); })
            .catch(this.handleError);
    };
    BackendService.prototype.getDiscipline = function (id) {
        return this.http.get(this.url + 'disciplines/' + id)
            .map(function (response) { return response.json(); })
            .do(function (response) { return console.log(JSON.stringify(response)); })
            .catch(this.handleError);
    };
    BackendService.prototype.handleError = function (error) {
        // In a real world app, you might use a remote logging infrastructure
        var errMsg;
        if (error instanceof __WEBPACK_IMPORTED_MODULE_1__angular_http__["b" /* Response */]) {
            var body = error.json() || '';
            var err = body.error || JSON.stringify(body);
            errMsg = error.status + " - " + (error.statusText || '') + " " + err;
        }
        else {
            errMsg = error.message ? error.message : error.toString();
        }
        console.error(errMsg);
        return __WEBPACK_IMPORTED_MODULE_2_rxjs_Observable__["Observable"].throw(errMsg);
    };
    BackendService = __decorate([
        __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["c" /* Injectable */])(), 
        __metadata('design:paramtypes', [(typeof (_a = typeof __WEBPACK_IMPORTED_MODULE_1__angular_http__["c" /* Http */] !== 'undefined' && __WEBPACK_IMPORTED_MODULE_1__angular_http__["c" /* Http */]) === 'function' && _a) || Object])
    ], BackendService);
    return BackendService;
    var _a;
}());
//# sourceMappingURL=backend.service.js.map

/***/ })

},[538]);
//# sourceMappingURL=main.bundle.js.map