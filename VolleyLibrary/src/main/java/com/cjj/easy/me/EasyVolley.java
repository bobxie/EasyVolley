package com.cjj.easy.me;

import android.content.Context;

import com.cjj.easy.DefaultRetryPolicy;
import com.cjj.easy.Request;
import com.cjj.easy.RequestQueue;
import com.cjj.easy.cache.BitmapCache;
import com.cjj.easy.toolbox.ImageLoader;
import com.cjj.easy.toolbox.Volley;

public class EasyVolley {
	//声明请求队列对象
	private static RequestQueue mRequestQueue;
	//声明ImageLoader对象
	private static ImageLoader mImageLoader;
	//构造函数
	public EasyVolley(){}
	/**
	 * 初始化
	 */
	public static void init(Context context)
	{
		mRequestQueue = Volley.newRequestQueue(context);
		mImageLoader = new ImageLoader(mRequestQueue, new BitmapCache());
	}
	
	/**
	 * 获得请求队列对象RequestQueue
	 * @return RequestQueue
	 */
	public static RequestQueue getRequestQueue()
	{
		if (mRequestQueue != null) 
		{
			return mRequestQueue;
		} else
		{
			throw new IllegalStateException("RequestQueue should be initialize");
		}
	}
	/**
	 * 获得ImageLoader对象
	 * @return ImageLoader
	 */
	public static ImageLoader getImageLoader()
	{
		if (mImageLoader != null) 
		{
			return mImageLoader;
		} else
		{
			throw new IllegalStateException("ImageLoader not initialized");
		}
	}
	
	/**
	 * 设置tag, 添加请求
	 * @param request
	 * @param tag
	 */
	public static void addRequest(Request<?> request, Object tag)
	{
        if (tag != null)
        {
            request.setTag(tag);
        }
        mRequestQueue.add(request);
    }
	
	/**
	 * 根据tag 取消所有请求
	 * @param tag
	 */
	public static void cancelAll(Object tag)
	{
        mRequestQueue.cancelAll(tag);
    }
	
	/**
	 * 自定义请求超时
	 */
	public static void setTimeOutRequest(Request<?> request,int timeOut)
	{
		request.setRetryPolicy(new DefaultRetryPolicy(timeOut, 1, 1.0f));
	}
	
	
}
