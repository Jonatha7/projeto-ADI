const favoriteBtns = Array.from(document.querySelectorAll('.favorite-btn'))
favoriteBtns.forEach(btn => {
    const icon = btn.querySelector('i')
    let fav = parseInt(btn.dataset.fav)

    if(!fav){
        icon.classList.remove('fas')
        icon.classList.add('far')
    }

    icon.addEventListener('click', e => {
        e.stopPropagation()
        btn.click()	
    })

    btn.addEventListener('click', e => {
		let favBtn = e.target
        let {rep} = favBtn.dataset
        let fav = parseInt(favBtn.dataset.fav)

        if(fav){
            icon.classList.remove('fas')
            icon.classList.add('far')
            favBtn.dataset.fav =  '0'
        }else{
            icon.classList.add('fas')
            icon.classList.remove('far')
            favBtn.dataset.fav =  '1'
        }
		
		if(fav) {
			$.ajax({
	            url: 'http://localhost:8088/project/delete/' + rep,
				data: {},
	            type: 'DELETE'
        	})
			
		} else {
			let json = document.getElementById(rep).value
			let data = JSON.parse(json.replaceAll('\'', '\"'))
			data = JSON.stringify(data)
			console.log(data)
			$.ajax({
	            url: 'http://localhost:8088/project/save',
				data: data,
				headers: {
					'Accept': 'application/json',
        			'Content-Type': 'application/json; charset=utf-8'
    			},
	            type: 'POST'
        	})
		}
    })
})