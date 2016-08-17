package com.ouyang.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/8/11 0011.
 */
public class MyAdapter extends BaseAdapter {

    Context context;
    List<String> list;
//    Map<String, Bitmap> map = new HashMap<>();
    String[] imgurl=
            {
                    "http://c.hiphotos.baidu.com/image/w%3D310/sign=798e237a40a98226b8c12d26ba82b97a/f3d3572c11dfa9ec1859f71861d0f703918fc14e.jpg",
                    "http://a.hiphotos.baidu.com/image/w%3D310/sign=3e11a6462b381f309e198ba899014c67/d6ca7bcb0a46f21f1e37ca2df5246b600c33ae49.jpg",
                    "http://f.hiphotos.baidu.com/image/w%3D310/sign=4bc13f8cc81349547e1eee65664e92dd/4610b912c8fcc3ced6e34e3e9145d688d43f209f.jpg",
                    "http://h.hiphotos.baidu.com/image/w%3D310/sign=f2507c8a52da81cb4ee685cc6266d0a4/cefc1e178a82b90115ae574d708da9773912ef49.jpg",
                    "http://c.hiphotos.baidu.com/image/w%3D310/sign=35cdca03442309f7e76fab13420f0c39/faf2b2119313b07eb0078f9f0ed7912396dd8ceb.jpg",
                    "http://d.hiphotos.baidu.com/image/w%3D310/sign=9073f4d6f436afc30e0c39648319eb85/6f061d950a7b0208e8c886f761d9f2d3572cc8a7.jpg",
                    "http://h.hiphotos.baidu.com/image/w%3D310/sign=e0b34e110846f21fc9345852c6256b31/96dda144ad34598285cee5780ef431adcaef8497.jpg",
                    "http://a.hiphotos.baidu.com/image/w%3D310/sign=996c1bfba244ad342ebf8186e0a20c08/95eef01f3a292df5e8b11503bf315c6034a873b1.jpg",
                    "http://e.hiphotos.baidu.com/image/w%3D310/sign=d287f328d3160924dc25a41ae406359b/f703738da9773912d761111ffb198618367ae20d.jpg",
                    "http://c.hiphotos.baidu.com/image/w%3D310/sign=ff9e75642b34349b74066884f9eb1521/00e93901213fb80ea15ff5a935d12f2eb83894c6.jpg",
                    "http://h.hiphotos.baidu.com/image/w%3D310/sign=36f71b38b23533faf5b6952f98d3fdca/ac6eddc451da81cb16f411fb5166d016092431f1.jpg",
                    "http://b.hiphotos.baidu.com/image/w%3D310/sign=717bf2b10ef3d7ca0cf63977c21ebe3c/64380cd7912397dd481d2d8b5b82b2b7d1a287dd.jpg",
                    "http://f.hiphotos.baidu.com/image/w%3D310/sign=0af695429d2f07085f052c01d924b865/730e0cf3d7ca7bcb87664022bd096b63f624a89a.jpg",
                    "http://b.hiphotos.baidu.com/image/w%3D310/sign=8885b850602762d0803ea2be90ed0849/2934349b033b5bb5e883278d35d3d539b600bc68.jpg",
                    "http://c.hiphotos.baidu.com/image/w%3D310/sign=126415f6eb24b899de3c7f395e071d59/0b46f21fbe096b63cc7861a60e338744eaf8ace6.jpg",
                    "http://a.hiphotos.baidu.com/image/w%3D310/sign=23297efbb44543a9f51bfccd2e178a7b/63d9f2d3572c11df39dc1f2c602762d0f703c2b4.jpg",
                    "http://b.hiphotos.baidu.com/image/w%3D310/sign=0a207e4f7bf0f736d8fe4a003a55b382/4d086e061d950a7ba34c06f409d162d9f2d3c981.jpg",
                    "http://d.hiphotos.baidu.com/image/w%3D310/sign=95c627055043fbf2c52ca022807fca1e/91ef76c6a7efce1bce8ccac7ac51f3deb48f6513.jpg",
                    "http://d.hiphotos.baidu.com/image/w%3D310/sign=d95566ec542c11dfded1b92253266255/d62a6059252dd42a93b8a0a4003b5bb5c9eab808.jpg",
                    "http://e.hiphotos.baidu.com/image/w%3D310/sign=bdc1fe02808ba61edfeece2e713697cc/50da81cb39dbb6fd5e921dee0b24ab18962b3728.jpg",
                    "http://e.hiphotos.baidu.com/image/w%3D310/sign=ea00674bab773912c4268360c8188675/a1ec08fa513d269745ed29ab57fbb2fb4316d80d.jpg",
                    "http://g.hiphotos.baidu.com/image/w%3D310/sign=02ad3ef0f8edab6474724bc1c737af81/e824b899a9014c08c8a4689e097b02087bf4f46a.jpg",
                    "http://a.hiphotos.baidu.com/image/w%3D310/sign=7df35f0de51190ef01fb94defe199df7/a71ea8d3fd1f41346fcc8a9e271f95cad0c85e53.jpg",
                    "http://c.hiphotos.baidu.com/image/w%3D310/sign=b7a32482b1b7d0a27bc9029cfbee760d/2cf5e0fe9925bc3146f749a85ddf8db1cb137053.jpg",
                    "http://h.hiphotos.baidu.com/image/w%3D310/sign=ce0ed954554e9258a63480efac82d1d1/c2fdfc039245d68813128394a7c27d1ed21b24e9.jpg",
                    "http://b.hiphotos.baidu.com/image/w%3D310/sign=abf5ab50a60f4bfb8cd09855334d788f/29381f30e924b89980f6bdf76c061d950b7bf633.jpg",
                    "http://f.hiphotos.baidu.com/image/w%3D310/sign=eab0abc37a310a55c424d8f587454387/0b7b02087bf40ad17f5ff6d3542c11dfa9ecce42.jpg",
                    "http://c.hiphotos.baidu.com/image/w%3D310/sign=428d7c2fa818972ba33a06cbd6cc7b9d/a8773912b31bb0516a82f313357adab44aede063.jpg",
                    "http://d.hiphotos.baidu.com/image/w%3D310/sign=a477958fa3ec08fa260015a669ee3d4d/c8ea15ce36d3d539e5743ef33987e950352ab0b0.jpg",
                    "http://e.hiphotos.baidu.com/image/w%3D310/sign=b97e90d6e41190ef01fb94defe1b9df7/a71ea8d3fd1f4134ab414545261f95cad1c85e92.jpg",
                    "http://f.hiphotos.baidu.com/image/w%3D310/sign=447b5722a344ad342ebf8186e0a30c08/95eef01f3a292df535a659dabe315c6035a8739c.jpg",
                    "http://e.hiphotos.baidu.com/image/w%3D310/sign=a42dac83700e0cf3a0f748fa3a47f23d/cb8065380cd79123a3a295e5af345982b3b780e4.jpg",
                    "http://b.hiphotos.baidu.com/image/w%3D310/sign=8bb56be46f224f4a5799751239f69044/c75c10385343fbf28b5fbbe1b27eca8064388f84.jpg",
                    "http://c.hiphotos.baidu.com/image/w%3D310/sign=be983bf2369b033b2c88fadb25cf3620/3801213fb80e7bec3e5525172d2eb9389a506bbc.jpg",
                    "http://b.hiphotos.baidu.com/image/w%3D310/sign=dc858c03d739b6004dce09b6d9513526/2e2eb9389b504fc21c293fe5e7dde71191ef6dd8.jpg",
                    "http://g.hiphotos.baidu.com/image/w%3D310/sign=c10c8f64bb99a9013b355d372d950a58/f31fbe096b63f624b55c6a738444ebf81a4ca3b9.jpg",
                    "http://h.hiphotos.baidu.com/image/w%3D310/sign=a0094a14abec8a13141a51e1c7029157/242dd42a2834349bf41e007ecbea15ce36d3be35.jpg",
                    "http://f.hiphotos.baidu.com/image/w%3D310/sign=0a8be478abec8a13141a51e1c7029157/242dd42a2834349b5e9cae12cbea15ce37d3be8b.jpg",
                    "http://f.hiphotos.baidu.com/image/w%3D310/sign=ebd1fedfb01c8701d6b6b4e7177e9e6e/21a4462309f790525d8007050ff3d7ca7bcbd56a.jpg",
                    "http://c.hiphotos.baidu.com/image/w%3D310/sign=718e46f77af0f736d8fe4a003a57b382/4d086e061d950a7bd8e23e4c08d162d9f3d3c967.jpg",
                    "http://c.hiphotos.baidu.com/image/w%3D310/sign=0e729645d588d43ff0a997f34d1ed2aa/79f0f736afc37931f824b719e8c4b74543a911b5.jpg",
                    "http://d.hiphotos.baidu.com/image/w%3D310/sign=4482cbc37d3e6709be0043fe0bc69fb8/7a899e510fb30f24a1a3dbe7ca95d143ad4b031c.jpg",
                    "http://g.hiphotos.baidu.com/image/w%3D310/sign=b701c0bc5166d0167e199829a72ad498/4b90f603738da977b7c8c55ab351f8198718e3d8.jpg",
                    "http://f.hiphotos.baidu.com/image/w%3D310/sign=8e8a9dd63b12b31bc76ccb28b6193674/09fa513d269759eec06a615ab0fb43166d22df6d.jpg",
                    "http://g.hiphotos.baidu.com/image/w%3D310/sign=7d8e5961cbea15ce41eee60886013a25/7aec54e736d12f2ec78d99844dc2d5628435684d.jpg"};

    LruCache<String, Bitmap> lruCache;
    //软引用保存图片,并把所有的软引用保存在一个map集合中-----软引用只能保存一张图片;
    Map<String,SoftReference<Bitmap>> soft = new HashMap<>();
    public MyAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
        //初始化 ,参数为最大max值
        lruCache = new LruCache<String, Bitmap>((int) (Runtime.getRuntime().maxMemory()/4)){
            // 每添加一张图片,就会调用这个方法
            @Override
            protected int sizeOf(String key, Bitmap value) {
                //返回一张图片, 用来lru进行统计还剩多少空间
                return value.getWidth()*value.getRowBytes();
            }
        };
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.items,null);
        }
        TextView tv = (TextView) convertView.findViewById(R.id.tv);
        final ImageView iv = (ImageView) convertView.findViewById(R.id.iv);
        tv.setText(list.get(position));

        final String url = imgurl[position];

        //将url设置标记,防止闪屏---每次读取时查看是否有被标记;
        iv.setTag(url);

        //使用RxAndroid
//        Observable<Bitmap> mapApp = Observable.create(new Observable.OnSubscribe<Bitmap>() {
//            @Override
//            public void call(Subscriber<? super Bitmap> subscriber) {
//                Bitmap bm = lruCache.get(url);
//                if (bm != null){
//                    subscriber.onNext(bm);
//                }else{
//                    subscriber.onCompleted();
//                }
//            }
//        });
//
//        Observable<Bitmap> softApp = Observable.create(new Observable.OnSubscribe<Bitmap>() {
//            @Override
//            public void call(Subscriber<? super Bitmap> subscriber) {
//                Bitmap bm = soft.get(url).get();
//                if (bm != null){
//                    subscriber.onNext(bm);
//                }else{
//                    subscriber.onCompleted();
//                }
//            }
//        });
//
//        Observable<Bitmap> sdApp = Observable.create(new Observable.OnSubscribe<Bitmap>() {
//            @Override
//            public void call(Subscriber<? super Bitmap> subscriber) {
//                File file = new File(context.getFilesDir(), StringUtils.getMD5Str32byte(url));
//                Bitmap bm = BitmapFactory.decodeFile(file.getPath());
//                if (bm != null){
//                    subscriber.onNext(bm);
//                }else{
//                    subscriber.onCompleted();
//                }
//            }
//        });
//
//        Observable<Bitmap> netApp = Observable.create(new Observable.OnSubscribe<Bitmap>() {
//            @Override
//            public void call(Subscriber<? super Bitmap> subscriber) {
//                MyTask task = new MyTask(url,iv);
//
//                task.execute();
//                Bitmap bm = task.doInBackground();
//                if (bm != null){
//                    subscriber.onNext(bm);
//                }else{
//                    subscriber.onCompleted();
//                }
//            }
//        });
//
//        Observable.concat(mapApp,softApp,sdApp).first().subscribe(new Action1<Bitmap>() {
//            @Override
//            public void call(Bitmap bitmap) {
//                iv.setImageBitmap(bitmap);
//            }
//        });

        // 先从强引用获取,再到弱引用,再到本地,在到联网请求
        if (lruCache.get(url) != null){
            //从内存读取
            iv.setImageBitmap(lruCache.get(url));
            Log.e("print", "getView: 来自map内存存储");
        }else{
            //从软引用里面获取图片
            if (soft.get(url)!=null){
                iv.setImageBitmap(soft.get(url).get());
                Log.e("print", "getView: 来自软引用的存储" );
            }else {
                //从本地下载
                File file = new File(context.getFilesDir(), StringUtils.getMD5Str32byte(url));
                Bitmap bm = BitmapFactory.decodeFile(file.getPath());
                if (bm != null) {
                    iv.setImageBitmap(bm);
                    Log.e("print", "getView: 来自本地存储");
                    //将本地图片添加到内存
                    lruCache.put(url, bm);
                } else {
                    //联网下载
                    new MyTask(url, iv).execute();
                }
            }
        }
        return convertView;
    }

    /**
     * 联网请求下载
     */
    class MyTask extends AsyncTask<String, String, Bitmap>{

        String urlString;
        ImageView iv;

        public MyTask(String urlString, ImageView iv) {
            this.urlString = urlString;
            this.iv = iv;
        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            Bitmap bm = null;
            InputStream in = null;
            try {
                URL url = new URL(urlString);
                try {
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    in = conn.getInputStream();
                    bm = BitmapFactory.decodeStream(in);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } finally {
                if (in != null){
                    try {
                        in.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return bm;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            FileOutputStream fos = null;
            File file = new File(context.getFilesDir(),StringUtils.getMD5Str32byte(urlString));
            try {
                fos = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.JPEG,100,fos);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            if (urlString.equals(iv.getTag())) {
                iv.setImageBitmap(bitmap);
                Log.e("print", "getView: 来自网络存储");
            }
        }
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }


}
