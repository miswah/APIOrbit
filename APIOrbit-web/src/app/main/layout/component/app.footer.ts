import { Component } from '@angular/core';

@Component({
    standalone: true,
    selector: 'app-footer',
    template: `<div class="layout-footer">
        API Orbit by
        <a href="https://github.com/miswah" target="_blank" rel="noopener noreferrer" class="text-primary font-bold hover:underline">Miswah</a>
    </div>`
})
export class AppFooter {}
