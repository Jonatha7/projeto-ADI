/* Pagination beginning */

const cardContainers = Array.from(document.querySelectorAll(".card-container"))

cardContainers.forEach(container => {
    let group = parseInt(container.dataset.group)
    if(group == 1)
        container.style.display = 'flex'
    else
        container.style.display = 'none'
})

let currentPage = document.querySelector('.page-item.active')
const pageItems = Array.from(document.querySelectorAll(".pagination li a"))
const lastPage = pageItems[pageItems.length - 2]
const prevBtn = pageItems[0]
const prev = prevBtn.parentNode
const nextBtn = pageItems[pageItems.length - 1]
const next = nextBtn.parentNode

pageItems.forEach(item => {
    if(item == nextBtn || item == prevBtn){
        const icon = item.getElementsByTagName('i')[0]
        icon.addEventListener('click', e => {
            e.stopPropagation()
            item.click()
        })
    }

    item.addEventListener('click', e => {
        let page = e.target
        let which = page.id
        if(page != currentPage){
            if(which == 'prev'){
                currentPage.classList.remove('active')
                currentPage = currentPage.previousElementSibling
                currentPage.classList.add('active')
                console.log(currentPage.querySelector("a").id == '1')
                if(currentPage.querySelector("a").id == '1')
                    page.parentNode.classList.add('disabled')

                next.classList.remove('disabled')
            }else if(which == 'next'){
                currentPage.classList.remove('active')
                currentPage = currentPage.nextElementSibling
                currentPage.classList.add('active')

                if(currentPage.querySelector("a").id == lastPage.id)
                    page.parentNode.classList.add('disabled')

                prev.classList.remove('disabled')
            }else{
                currentPage.classList.remove('active')
                currentPage = page.parentNode
                currentPage.classList.add('active')

                if(currentPage.querySelector("a").id == lastPage.id){
                    next.classList.add('disabled')
                    prev.classList.remove('disabled')
                }else if(currentPage.querySelector("a").id == '1'){
                    prev.classList.add('disabled')
                    next.classList.remove('disabled')
                }else{
                    prev.classList.remove('disabled')
                    next.classList.remove('disabled')
                }
            }
            const index = currentPage.querySelector('a').id
            cardContainers.forEach(container => {
                let group = parseInt(container.dataset.group)
                if(group == index)
                    container.style.display = 'flex'
                else
                    container.style.display = 'none'
            })
        }
    })
})

/* Pagination end */

/* Chart beginning */
const languages = [
    {name: 'JavaScript', color: '#efff45'},
    {name: 'PHP', color: '#4a8cff'},
    {name: 'C', color: '#a1a1a1'},
    {name: 'C++', color: '#4f4f4f'},
    {name: 'Java', color: '#ffa742'},
    {name: 'C#', color: '#ab3dff'},
    {name: 'Python', color: '#252edb'},
    {name: 'Ruby', color: '#ff2e2e'},
    {name: 'Go', color: '#4ad4ff'},
    {name: 'Swift', color: '#d66f38'},
    {name: 'R', color: '#efff45'},
]
$.ajax({
	url: 'http://localhost:8088/json/language', //método que irá retornar os objetos de linguagens favoritas
	crossDomain: true,
	data: {},
	dataType: 'json',
	/* CORS
	headers: {
		'Accept': 'application/json',
		'Content-Type': 'application/json; charset=utf-8',
		'Access-Control-Allow-Origin': 'http://localhost',
		'Access-Control-Allow-Methods': 'GET',
		'Access-Control-Allow-Headers': '*',
		'Access-Control-Max-Age: 86400': '86400'
	},
	*/
	type: 'GET'
}).done(data => {
	data.forEach(favorite => {
    languages.forEach(language => {
        if(language.name == Object.keys(favorite)[0]) // retorna nome da linguage
			favorite.color = language.color
    })
    if(!favorite.color)
        favorite.color = ''
	})

	const someColors = [
	    '#853c3c',
	    '#855e3c',
	    '#73853c',
	    '#48853c',
	    '#3c8567',
	    '#de90da'
	]
	let colorIndex = 0
	
	const favoriteNames = data.map(favorite => Object.keys(favorite)[0])
	const favoriteQuantities = data.map(favorite => Object.values(favorite)[0])
	const favoriteColors = data.map(favorite => {
	    color = favorite.color ? favorite.color : someColors[colorIndex]
	    if(color == someColors[colorIndex])
	        colorIndex++
	    return color
	})
	
	const ctx = document.getElementById('languages-chart').getContext('2d')
	const myChart = new Chart(ctx, {
	    type: 'doughnut',
	    data: {
	        labels: favoriteNames,
	        datasets: [{
	            data: favoriteQuantities,
	            backgroundColor: favoriteColors,
	            borderColor: favoriteColors,
	            borderWidth: 1
	        }]
	    }
	})
})