"use strict";

var array = [50, 30, 5, 12]

d3.select('body')
  .append('svg')
  .attr('width', 400)
  .selectAll('circle')
  .data(array)
  .enter()
  .append('circle')
  .attr('cx', function(d, i) {
    return 50 + i * 100
  })
  .attr('cy', 50)
  .attr('r', function(d) {
    return d
  })
