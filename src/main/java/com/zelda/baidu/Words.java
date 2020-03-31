package com.zelda.baidu;

import com.zelda.entity.BaiduWords;
import com.zelda.util.HttpUtil;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 百度文字识别
 * @author mantou
 */
public class Words {

	/**
	 * 通用文字识别
	 */
	private static final String url = "https://aip.baidubce.com/rest/2.0/ocr/v1/general_basic";

	public static String commonWords(BaiduWords words){
		if(StringUtils.isEmpty(words.getImage())){
			throw new NullPointerException("image不能为空");
		}
		Map<String,String> map = new HashMap<>(7);
		map.put("image",words.getImage());
		if(!StringUtils.isEmpty(words.getUrl())){
			map.put(words.url,words.getUrl());
		}
		if(!StringUtils.isEmpty(words.getLanguage_type())){
			map.put("language_type",words.getLanguage_type());
		}
		if(!StringUtils.isEmpty(words.getDetect_direction())){
			map.put("detect_direction",words.getDetect_direction().toString());
		}
		if(!StringUtils.isEmpty(words.getDetect_language())){
			map.put("detect_language",words.getDetect_language().toString());
		}
		if(!StringUtils.isEmpty(words.getParagraph())){
			map.put("paragraph",words.getParagraph().toString());
		}
		if(!StringUtils.isEmpty(words.getProbability())){
			map.put("probability",words.getProbability().toString());
		}
		return HttpUtil.postRequest(url+"?access_token="+AuthService.getToken(),map);
	}

	private static final String IMG = "data:image/png;base64,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";

	private static final String img2 = "iVBORw0KGgoAAAANSUhEUgAAAHoAAAA0CAYAAABFCyz4AAAEGWlDQ1BrQ0dDb2xvclNwYWNlR2VuZXJpY1JHQgAAOI2NVV1oHFUUPrtzZyMkzlNsNIV0qD8NJQ2TVjShtLp/3d02bpZJNtoi6GT27s6Yyc44M7v9oU9FUHwx6psUxL+3gCAo9Q/bPrQvlQol2tQgKD60+INQ6Ium65k7M5lpurHeZe58853vnnvuuWfvBei5qliWkRQBFpquLRcy4nOHj4g9K5CEh6AXBqFXUR0rXalMAjZPC3e1W99Dwntf2dXd/p+tt0YdFSBxH2Kz5qgLiI8B8KdVy3YBevqRHz/qWh72Yui3MUDEL3q44WPXw3M+fo1pZuQs4tOIBVVTaoiXEI/MxfhGDPsxsNZfoE1q66ro5aJim3XdoLFw72H+n23BaIXzbcOnz5mfPoTvYVz7KzUl5+FRxEuqkp9G/Ajia219thzg25abkRE/BpDc3pqvphHvRFys2weqvp+krbWKIX7nhDbzLOItiM8358pTwdirqpPFnMF2xLc1WvLyOwTAibpbmvHHcvttU57y5+XqNZrLe3lE/Pq8eUj2fXKfOe3pfOjzhJYtB/yll5SDFcSDiH+hRkH25+L+sdxKEAMZahrlSX8ukqMOWy/jXW2m6M9LDBc31B9LFuv6gVKg/0Szi3KAr1kGq1GMjU/aLbnq6/lRxc4XfJ98hTargX++DbMJBSiYMIe9Ck1YAxFkKEAG3xbYaKmDDgYyFK0UGYpfoWYXG+fAPPI6tJnNwb7ClP7IyF+D+bjOtCpkhz6CFrIa/I6sFtNl8auFXGMTP34sNwI/JhkgEtmDz14ySfaRcTIBInmKPE32kxyyE2Tv+thKbEVePDfW/byMM1Kmm0XdObS7oGD/MypMXFPXrCwOtoYjyyn7BV29/MZfsVzpLDdRtuIZnbpXzvlf+ev8MvYr/Gqk4H/kV/G3csdazLuyTMPsbFhzd1UabQbjFvDRmcWJxR3zcfHkVw9GfpbJmeev9F08WW8uDkaslwX6avlWGU6NRKz0g/SHtCy9J30o/ca9zX3Kfc19zn3BXQKRO8ud477hLnAfc1/G9mrzGlrfexZ5GLdn6ZZrrEohI2wVHhZywjbhUWEy8icMCGNCUdiBlq3r+xafL549HQ5jH+an+1y+LlYBifuxAvRN/lVVVOlwlCkdVm9NOL5BE4wkQ2SMlDZU97hX86EilU/lUmkQUztTE6mx1EEPh7OmdqBtAvv8HdWpbrJS6tJj3n0CWdM6busNzRV3S9KTYhqvNiqWmuroiKgYhshMjmhTh9ptWhsF7970j/SbMrsPE1suR5z7DMC+P/Hs+y7ijrQAlhyAgccjbhjPygfeBTjzhNqy28EdkUh8C+DU9+z2v/oyeH791OncxHOs5y2AtTc7nb/f73TWPkD/qwBnjX8BoJ98VQNcC+8AAAA4ZVhJZk1NACoAAAAIAAGHaQAEAAAAAQAAABoAAAAAAAKgAgAEAAAAAQAAAHqgAwAEAAAAAQAAADQAAAAAYhVSWwAAFwBJREFUeAHtWwuY1tPWX+/MNMmI7kVTzRSSaxcipSOKckkoxXFJpMO4FOVSJKdGQ0pRSHIUjXOcOCRfbimim8tHShfRTPcp0VWqmXm/32/tvf+Xd2beN/q+5/keM6ve/76tvdbaa+3r2nsiUYBUwJ9eA0l/+hZWNFA1UGHoctIRKgxdYehyooFy0syKEV1h6HKigXLSzIoRXWHocqKBctLMcj+iy4u/6P/M0FOmTJHMzExpnNlYww8//PD/5di54oorpPlpp8lDDz0kq1atOiQZC7YUSMOGjeTYY4+V008/XV5+eeoh0fvfrJwSjxgbfucdd0qU/+ApjUQioVDrIi+CshNPOkmefPJJj9yaNWtk+/btBgXf/fv3e2XxIsuXL5dmzZqVifLVV1/J2rVrVQ4iUSaCL5+mvHymzjjjDKlfvz6jIdi8ebN8+uk8KSwqkvHjx+vv39OnS8fzzw/hHWwib02e7Nq1U9G3bdsm+/aF2/zFF1/If73zDrRpIBKFPiMu5XNhm2666Sb55ZdfZNKkSVbvIkkYlsXFUUlCebGlAu3L/fffL/Xq1fMJlBKLa+ivv/5aFi1ehGpUphVIoyTPHBqfSo7IgcLCEPniaJFXjzWdQUJIMYnXXntNbr31VunVs6dMeOaZmFKTZGeaOfOdEvw9+ZysVk7md+16qUyZUnJ0UYmFhb6ctevUkXP/8pdS+R5M5rr8fKAZzTDIyMgIVVuwYIE8OXas1RmLLG5MyNzq1WtIpUopMnUq5XZ4LhrWf58+fRIaGn2kbOBINeBCpDSqY1yLDAozAzgsgfFdnsY8WiwsCQsXLpQ777wDPbZYcl99VQYNGlQSCTmGS2n8Y9CtnMwtLiqOKQQdyEM+BgzV3jdcLykpcft+CTrBjPx16/wkSGZmZvppL8ZZkRoBeKPZ8Pdbh6KQZVw56mg0tv1KLe4nRC4W88hq1aRhgwaS3iBd0tMbSIP0dKxBCDWdLkna01iLU0nZYMQMCBuDunr1arn66qtDU93kyZMlLy8vBpOcfPDjJma+JflEOe3EwKxZs2Tzpk1ebpUqVSQr63ZN79q1S0Zkj5AOHc476CWHFdevX4+v4c8ZrGHDhkrPfSKYe1NSkjAFUx78AqImJyc7NA2jnKI5VwNMv4hI48aNdf2vWbOm5rtWHXHEEZqO94nbfS+88ELhryzgurBv3z4UByR2yF5vRQYaHQl3UYclP//8s1x55ZVYz3d4ebRLv379JCMjw8tzkWKdGYAA+kcdVU0ee+wxVQhnAiqGIWHIkMHy00/bNO4Uogn7GYspNAiXX3456B0lzz77rDz6aLbs3r1HOxXx7r333iBqmfENGzbasgim3uqYeiuFcG/PyhL+Zs+eLd27d/fKfvjhB6lRo4bUqlVLirBfYPs5Obu2UJWYB2TevHly+OGHS/aIbHlizBOYGUSqVq2qHcAjVkYkrqHLqONlqy2tFv3RbYqTIoEeConYQ2OBm6oePXro5opaJT1indXmbCj70Vh0TRt2BvGwypXlqquuKhUvOzsb+cbQsUeoRYsWyeeff+7V4+gbMGCAl969+1eNYwciz2CvkAXjpKWleeXBCI307bff0hKyevX3DNCGqOz77Td58803YTSzgWUnvOiii3RpiN2vOPlM21AfQ5jGdiOa/KgXh7d02VLNID43wQcDcafuhARMq4wUpSA7wTV0CYs3d+5caY+Nz6pV32sOjVwMnFNOOUX+ibUzVhmOfCjf0uzfv78cd9xx+guu7Y5lqA4IjRw5El+UKkJEOmPW4pGIcPPNN8sxR2MHizLuiHfu2CmjHhulZaV9/vWv1+TGG2+UPr1vFJ40aBDCnl9/lT7I7927t4Y33HCDvPXWW6ZQvz5/l8n2Ezh6aWmOaI7sWFiBk4mDFs1buGjc8JAM7RpFDjqjBlhRSI4ILcPHKbsQu3OeWTmSd+D4FdFpgX0lIi1PayFcO4888sgApZhogKnjybWR0/Q2/IJHOsc/KBxPEZ988gmIghD+p6amyPARIzwmnG7vGThQy8wME5UXXpykS4yHFIhEo2apIC/fKK7dLmSFCHb45mRiRqbh79qvGLZtJnB1bableeDAAVm3Yb2mWNK69Rm2JH6QcOqeM2cOzsejdTohKW28kUH2B86JnGqCYKYdKyTK2DhOmbfddpv8+OOPHqoz1oUXdJJ/vPSScFOUGMjMqFZxlTcnTB0ImmU4O/5GOCo667YslUWR8OnZs5c0adLEJTXkCB03bpxZUpDD0Tl69Ggxy0EI1evAlMdyA4LpYl6OihvFur9bRubkSH5gk8n2j3v6aamCZYg6Mi1DO3TDZnnZTA6WLxYvlqJC07nYsdq1axcWqIxUQkN/+eWX2AR8ZgRwUigxx90YP5a+biQcPhpDIW/qc5Ns2LhBUbmTTMKHO9BB2Ozcd999sSRKT5OmMamvWmdPSGlUZata/m5t40zCNVUpoCytyuEydOhQTXOkcM/ATpj3Y55Uw2aKaYMsMu2VV+TBBx8s0RF74sx/8sknywcffACcaZ5MDwweLMmVkmX4I8ONuCDEjevj2DwSXPvZGZ5Gp/IsbCMU3VujqT+tJTL3YzsbIadO3TpSu3ZtWxI/SGjoTZs2KwXVJT9WeVSpy6NyTR+LYWYFJB6V/fT4p6U7pmyea3lGr9+gvkwYP0Hat28fU7HspBslQZNyVBBYZlcCTzanIK7dL0x+wSBCXvLfs/dX6XZ5N9myuUC2YffvdrkWyTbVNHjHzp3y3HPPhTZtxONswB93xMofeTRi3759Zf78+U5dSvKYY47RkEok/2II6+RVL5lKbRrjH7cMf9tEWbBwgdJg+9nBDhYSrtGbN2+y5zjTgJo1akqdOnWlTj384EnSX93aUrfUnkVxDHBEd+jQAS7VOyQpOUnozVm8aPHvMrJS8oxKwzraNmRgLWsaZvmD95JvvjENUFQz7qnkZUuXyVas7cV2rTWUzNfQh/kszYnPP6/HnyCOi69YscIzau1ataUafBAbNm70ZExNTRV3/qXkJOmMTKFjXaEczabj+fx59KK3kkDZWrZsqfGD+SQc0QUFBSqQthUtphvvYKcL3xS+KJwquQPNyMjwM39njLKwoW6D5/gwz03ThqQ1FTaGPIYsDhypuMP1FQ08g+oZiyOK51r6m+mnJ88CjPxXcSK49tprDfnAl6cHSwLnWrPmb4KhHdDw55xzDjaNP8nbb78tvfvc6PFfs+ZHPQ9z0ER1E2va5qZuykn+vAegM8e1v1WrVo58wjChoX/auhVETI+rjA1DPCP/ik0Lz46EFSvZwzmiTPPnfjxXd64UEhbSaS1sFBagCD2ZjaURWd66dWs9NplS83UK9esrVS10xieO48+85s2bM0MNynZUhTepKnb3dGzUr5+u3r7MzEzJhPcpMyNDGGe9QQM55U82Iw7yvIK1OtbQOzGtb926xQiHb7NmJ2h8MzqGAzpECOxA1JPfyUQdNeTF9hQj5LSeDFcs9w2mDRAbFYznzeuT8t2K5XEdWo43w4SG3vYznA5gQsHqocfFA/Z+40a0prCKhbTy/MRJqOpM5FNBuzS7ZAnyUfbQ0Ifk7v53+xVMtpa5TKVhE77xjXLIkuU0TqdOndSwB7ezNwRvzfqbTH5xshxeJU2uve5aGcijVwzwRs3nG5FTTz1VMbjsKYB/nVq+7nbs2OFR4JROIxNIw8TQIeyu203p1P/5uFWrXr2abP9lu7b/P6+/IQPu6u/RiheJu0bv3btXXYHOPvW8zUQ8ktZkFN5ZT0OXQF0UuQbRCxQo0Xz2YgUURKJhEbXno9C7GECcTjdnbBe6+gyJy5HEzdDvMTLrNs5soleF9Ebl4GjE6TwWvsH67/ONStu2bRXlu++sYwPy1cY+xsGu3Wb6ZZqzC8F1FLac7U9KSsZdAtpO5fAH4Bn/iiuuNFlo07ffLpW8vDwtS/SJO6J1qiBnMCKvDXBM0FfLuOuFQQaXXnqpn7TCuQzPsAF6piyMqCl2X/w3dWLK0etdPadcJemybS3Hj7jOKbEYZ9CJk55Hz2AeS7Cw2HpsD08O7Fbq6kF+EvzzRUhRHE693EyWBkuXLvXo0Pd8/PHHy0aszwUF5sTCOvT4bcIlysqVK2Xl8pWqQ+aTL30VztAqDhiyY6ow2jijf+LzDoAXPsxhG6a+NFWGDhvKorgQ19B6jgRn5YXP+vUbZFpurkmDLBk5RZELD+/cbHG6mTN7jnz22ae2PCKd4BA566yzVJiXprwka/PXapyfwThzBq8HddRawrzVCgF4qjyayRgMY68hNaWa0uyAfAaPt2RvTH/dFMZ8zVoYqMxyy4gBb4jKMrRzSRKvaVOzPn889+MQ/44dO6qziK7fIHB95ysXbxZjIcSgPorZ9QL6Z2egq7dVy1by5VdfKpkZM2ccuqHXbVinxDBLKEN+jEBGIUEjE5Guy169emmd7T9vx+uNTzXOEcWdNp36BPbWh4c9bGli/cLaz/KDAZ43HbhRoPsIzYxI2hHm8oESOvmcm5IdyGuKFzHUdC00zdI26ibSpsnS8XK8g+Eab/rkdLpEXayfzf/M41+t2pFyEnb9hcW4maJcrOxFDKVY/trxOcUAnP4pP6Fjp45qaLYvPy9fN22c1uNBeAGMwdy2dRt8wamSCiIaIl4ptZKk4Md0EgxmWJuKybhrDYIp424ymGs2RqmVUk0mkCZMmBBGiJOiATxbW+Y8shiIytFHH+3Vdvyx4mkejcW6mo9ICuRnO6gkyhNsYyraSEzLAqMz3DaPCSK+wwe3VvB+9ezVU49hjn/LFvYYhM1EkL/SsAy4PDig0Q1/K6srsGHTpk01RlpFRYWydAluzxJA2dKj4j333IN1pgC/LTZkvEC22HRf3PSYMe6kxbpiQQ/8mo0W2GJXxvWOZ0oFSPv999/jjvZDV5wwNErxifJ9FoG6qlu3rsb5MbdB2jW8PCIZnUbl78OHm/ZsCbePbVywYCHqsK4DP+ZyXMjnPmyPw/ht7z51djj+7c4x/mjenU//93SPP+tf1rWbzJgxQ6a+/LIlh3aBUHIyBhKPml6u317jEXOzU0T+2zpRLGqpQVxDl1ojkElPjVGGEadyYPoIuROdtIG6vDgwopvCwYOHxJ0evap2+lJtoCpvq8zjB4Ph3Iy+Wvxx6aY+xVSEUgSzjEJTNdBiZyVPHkQ4zU7Ho0KunQbCdM877zzNPvHEE/1dP/hThPPPO187yRmt7S2UHdq8VfN0SP6eyXESwFk/JQWDStlEZfmK7yzfsoNDMnRh4QFvtFJovn5wwN5ImVWfLjMQcr3OyMz0cvjilC8xEwKnX4tE2h999JGmHB83dRPH8ecU58BE8QVCPON5ncJ2LK9/OUIxIaf9t2a8FXrB6vjz5YqDeZ/MQ9TyR6xlK3OfXKSPFI3MxOWxSz1jjj8zLXCPc9hhh5kUSPFlaCI4JEPv3fub7VUMIiFDu95oRDDqDQpDRQ4ZMkSzXOkTTzxR5r2vq8vRceaZreXM1mfK6XjG+95772kR+XPP0KhRI00Tj8+gOl5wgeIyU9doi80g9lWMFsV86KUixOsUrgpfofAaNtixWLt79x7qDSOeeVVraLJznHCC2aW759CmRHTkU4cef8cEId2ge/aYVzDssKWd7QPoGo17vIpFjk3v2bMnkBXVI0ggA1Ga0IkeLmGKb8V4z0sfLmEn3kT37t1b1yzNKOXDyxD+HHA3ayAqp8EjxXPscKy9XMeC78wdfjgsWzY3dTuMRCOadFln3NhxOpMYPqb9vBq9Dp651994Q5YsWeKJcBKmcj0vI2cvbtKculgrLc3cy3v8kTdq1ChsvqKycOF88LJbcuS75QrRMuGQDM2HfUFw/lw/j+OMpnbi+iX06Mye/ZGeu72zMtB43Td02DD5O36JgBcMdEw4uACj9/bbb5fc3GnIMk9xeF3owJuOKRX++6pyGH5IXBrXjWRneB+jZOyVV16W1T+sDtTz2/8RnCIPPPCAmI2j4X9uwAGza9dub0xQW2lpVTF1+zyYN+vdWbJqxaqQPnliuOSSS3zEMmIBUmVgxMneuGkj9AWhAcnJKeEnQKRMRWmp/+F13vXXX69/sjIMZ+nOnTvrTzEMKRn/1FPe5YhfMxzLx2N5vs50/FnKKW3aNFz+gyk7Fx8zTHrB3UHTaM60RIAR4gxTXXpUeMNB18uwCKEU3cU5OfZRAeuhLZ27dPbaz6e/K3HRY8Dw79Kli0dj2bJlWsdlmOdURolWLaB3kbbL4fAVCju2f/3pSkqGf9jQc+HhoefM6kKqV/M3HGQTPQClaqFhugQvJflOjH7gmTPf1ntdPq2ht2rixInmLw2Az0Zx9PTrd4v8B1NdacBLAXYW1nf8MzIa6Z+m0P2ogALSuR/GdjdqTpPOvvTI8faqeg38GOJXw4b8Mx4jvpmPSnvFGpSNM0dwdmnXtp28mvuqXHzxxaBbQ8aMGSPzPvlUq5A/j4Hk4YAveciQ7a9c+TBdgsw+x58Pe2Cp43medemly52W672QcXTKChNO3VQWhWAP42aDUy6fyj6Fd07O9UgB+cDfAXv3+4FzMYXPeSzHrl1MERDi/2K8I7vmr3/Vi4Nu3bpJEbxH3M3s339A+t7ST3ZglPYOeM34x3pZWbfJli2+k4RjbujQh1U5M2fOlA7nnisbMNtQLiorCxskHkkMmOtAx19HNgq8SZbi6W6KZtaErcZ46TB27Bh5B39TRXxe0fCKkesp4Xk8VuDfeI3A8+VCODeIQ532uuYafUPHWYUyvvvuu4pP1g0bNdR47CySmZmJc//mEu/FFTnBJ6GhuWZ2u+wy6izUbj8JwXGOaNP2bI8VH8OvgvPeBygAjTNqU0rwRKVIFxyx2lnHCf3k92MqzsZTXCqLWPT6DMGo64m327x1euSRR/TRHhVFMPQi6BB9hTwJvC9/E89qL8CV5C/2j/x4OqBrljtitT6+ysOQoa29fDJ2/H3cQDmjAaCbd3j2o14OSV133XXebppHzhQsazPhFHHt57n7b7fcInw4wLvpEIBAi+anaZYZ0a6dBiuRqzNEK5BIaOg2bdpIKqaSfft+0xGpOiEBSK1xaKlu7Tpy1113emTbn9Nee6ttmTWbweej+yt7dJf77r0Pl/3+LMDKA2Ho/Xip6UYDj0t0j7qrRTbSGdnxP7vNWZIzMsfjzQjfaPPZT6+re2LWYacxLzB5geCAsrfAY4TMxk1088R8jiYanesZ1/v33/8A7URtFtBKpYA+DiAxoqDyKfgTXHrAgsBrSZ6LiUvgJQb/yoV/rjsfL3Y8wOjmCO/ff4DJ4m5MZSJz1baH+rsjUFxC6NLloiiewoR+uACP1qxZK4qREsVaHaKBtTPaoEFDi189irUv2q5t2ygMGIVfOoRbWgLPaqPVwQ9/JREqhqKiLVu1UrrYgEThoo3i/BnCCSbuvvse4FaPpqenR3FFGc3NzVW6zKsG+WPpB+ti7xBqL966BYtD8ccfH6W4TZpkRrFOh8pcAleZ0RNOaKqy/LTV6CD70ewQD1zuRF988UVXJYq9S6icev2jkHBEs+fwb5H4t73c/OzB7wicVZuddKKcDpdfySMVjwZpupmaNetd/ZNVHp9iR2+8HslNUteuXUu8cuSUNwHeM/7N9ugxo31/eRnEcnJGSn5+nm7S+IfpHNGnYhQ5iHf+5CxCXHVYYJQ1xkvPsmDQoIGydl0+lpieoUuVID7P+3PmzNU/36lZq6YWDcDI7XxhZ+w3tqini5uz4J/+kCePjBzlnGn+6LRNZmgHSVTAn10DXI4qoBxooMLQ5cDIbGKFoSsMXU40UE6aWTGiKwxdTjRQTppZMaLLiaH/B9TSZF56m1ZsAAAAAElFTkSuQmCC";

	private static final String img3 = "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";

	public static void main(String[] args) {
		BaiduWords baiduWords = new BaiduWords();
		baiduWords.setImage(img2);
		String result = Words.commonWords(baiduWords);
		System.out.println(result);
	}

}
