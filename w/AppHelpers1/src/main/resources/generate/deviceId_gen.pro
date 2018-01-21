clz=com.exilant.grx.test.DeviceIdGenerator
method=getOne
#d for static, n for normal public
type=n

toParam=deviceId

#properties specific to DeviceIdGenerator
#random or fixed
generateType=fixed

#if fixed list of device ids, if only one then will always give that, if more than will circle thru them
#if you have two test cases one to create and one to cancel then can repeat same id in two consecutive instances so same number gets created 
#and then cancelled
fixed.1=KJSD920343
fixed.2=KJSD920343

fixed.3=IJSD773018
fixed.4=IJSD773018