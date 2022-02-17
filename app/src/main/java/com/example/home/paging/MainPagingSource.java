package com.example.home.paging;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.paging.PagingSource;
import androidx.paging.PagingState;
import androidx.paging.rxjava3.RxPagingSource;

import com.example.home.model.HitsItem;
import com.example.home.model.Page;
import com.example.home.network.RetroInstance;
import com.example.home.utils.Constants;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

public class MainPagingSource extends RxPagingSource<Integer , HitsItem> {
    @Nullable
    @Override
    public Integer getRefreshKey(@NonNull PagingState<Integer, HitsItem> pagingState) {
        Integer anchorPosition = pagingState.getAnchorPosition();
        if (anchorPosition == null) {
            return null;
        }

        LoadResult.Page<Integer, HitsItem> anchorPage = pagingState.closestPageToPosition(anchorPosition);
        if (anchorPage == null) {
            return null;
        }

        Integer prevKey = anchorPage.getPrevKey();
        if (prevKey != null) {
            return prevKey + 1;
        }

        Integer nextKey = anchorPage.getNextKey();
        if (nextKey != null) {
            return nextKey - 1;
        }
        return pagingState.getAnchorPosition();
    }

    @NonNull
    @Override
    public Single<LoadResult<Integer, HitsItem>> loadSingle(@NonNull LoadParams<Integer> loadParams) {

        try{
            int pageNumber = loadParams.getKey() != null? loadParams.getKey():1;

            return RetroInstance.getRetroService()
                    .getRemotePage(Constants.URL_TAG,pageNumber)
                    .map(Page::getHits)
                    .map(hitsItems -> toLoadResult(hitsItems,pageNumber))
                    .onErrorReturn(LoadResult.Error::new);

        }catch (Exception e){
            return Single.just(new LoadResult.Error<>(e));
        }
    }

    private LoadResult<Integer , HitsItem> toLoadResult(List<HitsItem> hitsItems, int pageNumber) {
        return new LoadResult.Page(hitsItems,
                pageNumber == 1?null:pageNumber-1,
                pageNumber == 50?null:pageNumber+1,
                LoadResult.Page.COUNT_UNDEFINED,
                LoadResult.Page.COUNT_UNDEFINED);
    }
}
