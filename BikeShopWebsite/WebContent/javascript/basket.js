function addToBasket(bikeModel) {
	var retVal = confirm("Add " + bikeModel + " to shopping basket?");

	return retVal;
}

function removeFromBasket(serialNumber) {
	var retval = confirm("Remove bike " + serialNumber + " from shopping basket?")
	
	return retVal;
}