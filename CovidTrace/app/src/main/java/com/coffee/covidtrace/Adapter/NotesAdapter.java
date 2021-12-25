package com.coffee.covidtrace.Adapter;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.coffee.covidtrace.Data.History;
import com.coffee.covidtrace.Data.ThingsAnnouncement;
import com.coffee.covidtrace.Data.ThingsAnnouncementDao;
import com.coffee.covidtrace.R;
import com.coffee.covidtrace.Ui.ReportCaseActivity;
import com.coffee.covidtrace.Ui.home.fragment.PicExpandActivity;

import java.util.Arrays;

public class NotesAdapter extends ListAdapter<ThingsAnnouncement, NotesAdapter.NotesViewHolder> {

    String [] name_list;
    private Animator mCurrentAnimatorEffect;
    private int mShortAnimationDurationEffect;
    FrameLayout frameLayout;

//    Activity activity;
//    DiffUtil.ItemCallback<ThingsAnnouncement> diffCallback;

//    public NotesAdapter(String [] name_list){
//        this.name_list = name_list;
//    }

    public NotesAdapter(@NonNull DiffUtil.ItemCallback<ThingsAnnouncement> diffCallback) {
        super(diffCallback); }

    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mShortAnimationDurationEffect = parent.getResources().getInteger(
                android.R.integer.config_shortAnimTime);

        return new NotesViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.notes_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NotesViewHolder holder, int position) {

        ThingsAnnouncement current = getItem(position);
        Glide.with(holder.itemView.getContext())
                .load(current.getPic_authorities()) // set the img Url
                .apply(new RequestOptions().transform(new CenterCrop(), new RoundedCorners(16)))
                .into(holder.authorities_logo); // destination path

        holder.authorities_name.setText(current.getName_authorities());
        if (current.getTx_anouncement() != null){
            holder.authorities_content.setVisibility(View.VISIBLE);
            holder.authorities_content.setText(current.getTx_anouncement());
        }

        holder.post_date.setText(current.getDate());
        holder.post_time.setText(current.getTime());



        if (current.getPic_awareness()!=null){
            holder.pic_awareness.setVisibility(View.VISIBLE);
            Glide.with(holder.itemView.getContext())
                    .load(current.getPic_awareness()) // set the img Url
                    .apply(new RequestOptions().transform(new CenterCrop(), new RoundedCorners(16)))
                    .into(holder.pic_awareness); // destination path

            holder.pic_awareness.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent;
                    intent = new Intent(v.getContext(), PicExpandActivity.class);
                    intent.putExtra("image", current.getPic_awareness());
                    v.getContext().startActivity(intent);
//                    holder.expand_pic.setVisibility(View.VISIBLE);
//                    zoomImageFromThumb(holder.expand_pic, holder.pic_awareness, current.getPic_awareness(), holder.frameLayout);
                }
            });
        }


//        final NotesImgAdapter adapter = new NotesImgAdapter(new NotesImgAdapter.NotesImgDiff());
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(holder.nested_rv.getContext(), 2);
//        holder.nested_rv.setLayoutManager(gridLayoutManager);
//        holder.nested_rv.setAdapter(adapter);

    }

//    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
//    private void zoomImageFromThumb(ImageView expand_pic, ImageView pic_awareness, byte[] pic_awareness1, FrameLayout frameLayout) {
//        if (mCurrentAnimatorEffect != null) {
//            mCurrentAnimatorEffect.cancel();
//        }
//
////        Glide.with(holder.itemView.getContext())
////                .load(pic_awareness1) // set the img Url
////                .apply(new RequestOptions().transform(new CenterCrop(), new RoundedCorners(16)))
////                .into(holder.expand_pic); // destination path
//        Bitmap bmp = BitmapFactory.decodeByteArray(pic_awareness1, 0, pic_awareness1.length);
////        pic_awareness.setImageBitmap(Bitmap.createScaledBitmap(bmp, pic_awareness.getWidth(), pic_awareness.getHeight(), false));
//
//        expand_pic.setImageBitmap(bmp);
//
//
//        final Rect startBounds = new Rect();
//        final Rect finalBounds = new Rect();
//        final Point globalOffset = new Point();
//
//        pic_awareness.getGlobalVisibleRect(startBounds);
//        frameLayout.getGlobalVisibleRect(finalBounds, globalOffset);
//        startBounds.offset(-globalOffset.x, -globalOffset.y);
//        finalBounds.offset(-globalOffset.x, -globalOffset.y);
//
//        float startScale;
//        if ((float) finalBounds.width() / finalBounds.height()
//                > (float) startBounds.width() / startBounds.height()) {
//            // Extend start bounds horizontally
//            startScale = (float) startBounds.height() / finalBounds.height();
//            float startWidth = startScale * finalBounds.width();
//            float deltaWidth = (startWidth - startBounds.width()) / 2;
//            startBounds.left -= deltaWidth;
//            startBounds.right += deltaWidth;
//        } else {
//            // Extend start bounds vertically
//            startScale = (float) startBounds.width() / finalBounds.width();
//            float startHeight = startScale * finalBounds.height();
//            float deltaHeight = (startHeight - startBounds.height()) / 2;
//            startBounds.top -= deltaHeight;
//            startBounds.bottom += deltaHeight;
//        }
//
//        pic_awareness.setAlpha(0f);
//        expand_pic.setVisibility(View.VISIBLE);
//
//        expand_pic.setPivotX(0f);
//        expand_pic.setPivotY(0f);
//
//        // scale properties (X, Y, SCALE_X, and SCALE_Y).
//        AnimatorSet set = new AnimatorSet();
//        set
//                .play(ObjectAnimator.ofFloat(expand_pic, View.X,
//                        startBounds.left, finalBounds.left))
//                .with(ObjectAnimator.ofFloat(expand_pic, View.Y,
//                        startBounds.top, finalBounds.top))
//                .with(ObjectAnimator.ofFloat(expand_pic, View.SCALE_X,
//                        startScale, 1f)).with(ObjectAnimator.ofFloat(expand_pic,
//                View.SCALE_Y, startScale, 1f));
//        set.setDuration(mShortAnimationDurationEffect);
//        set.setInterpolator(new DecelerateInterpolator());
//        set.addListener(new AnimatorListenerAdapter() {
//            @Override
//            public void onAnimationEnd(Animator animation) {
//                mCurrentAnimatorEffect = null;
//            }
//
//            @Override
//            public void onAnimationCancel(Animator animation) {
//                mCurrentAnimatorEffect = null;
//            }
//        });
//        set.start();
//        mCurrentAnimatorEffect = set;
//
//        final float startScaleFinal = startScale;
//        expand_pic.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (mCurrentAnimatorEffect != null) {
//                    mCurrentAnimatorEffect.cancel();
//                }
//
//                // back to their original values.
//                AnimatorSet set = new AnimatorSet();
//                set.play(ObjectAnimator
//                        .ofFloat(expand_pic, View.X, startBounds.left))
//                        .with(ObjectAnimator
//                                .ofFloat(expand_pic,
//                                        View.Y,startBounds.top))
//                        .with(ObjectAnimator
//                                .ofFloat(expand_pic,
//                                        View.SCALE_X, startScaleFinal))
//                        .with(ObjectAnimator
//                                .ofFloat(expand_pic,
//                                        View.SCALE_Y, startScaleFinal));
//                set.setDuration(mShortAnimationDurationEffect);
//                set.setInterpolator(new DecelerateInterpolator());
//                set.addListener(new AnimatorListenerAdapter() {
//                    @Override
//                    public void onAnimationEnd(Animator animation) {
//                        pic_awareness.setAlpha(1f);
//                        expand_pic.setVisibility(View.GONE);
//                        mCurrentAnimatorEffect = null;
//                    }
//
//                    @Override
//                    public void onAnimationCancel(Animator animation) {
//                        pic_awareness.setAlpha(1f);
//                        expand_pic.setVisibility(View.GONE);
//                        mCurrentAnimatorEffect = null;
//                    }
//                });
//                set.start();
//                mCurrentAnimatorEffect = set;
//            }
//        });
//    }

    public static class NotesDiff extends DiffUtil.ItemCallback<ThingsAnnouncement> {

        @Override
        public boolean areItemsTheSame(@NonNull ThingsAnnouncement oldItem, @NonNull ThingsAnnouncement newItem) {
            return oldItem == newItem;
        }

        @SuppressLint("DiffUtilEquals")
        @Override
        public boolean areContentsTheSame(@NonNull ThingsAnnouncement oldItem, @NonNull ThingsAnnouncement newItem) {
            return oldItem.getDate().equals(newItem.getDate()) &&
                    oldItem.getName_authorities().equals(newItem.getName_authorities()) &&
                    Arrays.equals(oldItem.getPic_authorities(), newItem.getPic_authorities()) &&
                    oldItem.getTime().equals(newItem.getTime())&&
                    Arrays.equals(oldItem.getPic_awareness(), newItem.getPic_awareness()) &&
                    oldItem.getTx_anouncement().equals(newItem.getTx_anouncement());
        }
    }

//    @Override
//    public int getItemCount() {
//        return name_list.length;
//    }

    static class NotesViewHolder extends RecyclerView.ViewHolder{

        ImageView authorities_logo;
        ImageView pic_awareness, expand_pic;
        TextView authorities_name;
        TextView authorities_content;
        TextView post_date;
        TextView post_time;
        FrameLayout frameLayout;
//        RecyclerView nested_rv;

        public NotesViewHolder(@NonNull View itemView) {
            super(itemView);

            authorities_logo = itemView.findViewById(R.id.authorities_logo);
            authorities_name = itemView.findViewById(R.id.authorities_name);
            authorities_content = itemView.findViewById(R.id.authorities_content);
            post_date = itemView.findViewById(R.id.post_date);
            post_time = itemView.findViewById(R.id.post_time);
            pic_awareness = itemView.findViewById(R.id.iv_content);
            expand_pic = itemView.findViewById(R.id.iv_expanded_content);
            frameLayout = itemView.findViewById(R.id.container);
//            nested_rv = itemView.findViewById(R.id.recyclerView);
        }
    }
}
