import Vue from 'vue'
Vue.filter('formatDate', function (value) {
  var date = new Date(value)
  var year = date.getFullYear()
  var month = date.getMonth()
  var day = date.getDate()
  if (month < 10) {
    month = '0' + month
  }
  if (day < 10) {
    day = '0' + day
  }
  return year + '-' + month + '-' + day
})
Vue.filter('formatDateTime', function (value) {
  var date = new Date(value)
  var year = date.getFullYear()
  var month = date.getMonth() + 1
  var day = date.getDate()
  let hours = date.getHours()
  let minutes = date.getMinutes()
  if (month < 10) {
    month = '0' + month
  }
  if (day < 10) {
    day = '0' + day
  }
  if (hours < 10) {
    hours = '0' + hours
  }
  if (minutes < 10) {
    minutes = '0' + minutes
  }
  return year + '-' + month + '-' + day + ' ' + hours + ':' + minutes
})