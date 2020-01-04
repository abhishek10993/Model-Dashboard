import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'static-components',
  templateUrl: './static-components.component.html',
  styleUrls: ['./static-components.component.scss']
})
export class StaticComponentsComponent implements OnInit {

    similar_names = [
        { name: 'John Terry'},
        { name: 'John Doe' },
        { name: 'Thomas Mueller' },
        { name: 'Eric Thomas' },
        { name: 'Eric Doe' },
        { name: 'Ritch Hickey' },
    ]
  constructor() { }

  ngOnInit() {
  }

}
