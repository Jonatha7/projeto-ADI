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
		
		console.log(fav)

		if(fav) {
			$.ajax({
	            url: 'http://localhost:8088/project/delete/' + rep,  //definir URL do método
				data: {},
	            type: 'DELETE'
        	})
			
		} else {
			$.ajax({
	            url: 'http://localhost:8088/project/save', //definir URL do método
	            data: {
	                id: rep, //id do repositório
	                favorite: fav //boolean para saber se foi favoritado (1) ou desfavoritado (0)
	            },
	            type: 'POST'
        	})
		}
    })
})