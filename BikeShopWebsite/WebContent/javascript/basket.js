function addToBasket(bikeModel) {
	var retVal = confirm("Add " + bikeModel + " to shopping basket?");
	
	if(retVal) {
		document.location.href = '/BikeShopWebsite/BasketController';
	} 
}