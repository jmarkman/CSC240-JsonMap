package com.learningprojects.jmarkman.jsonmap;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

public class PopupAdapter implements GoogleMap.InfoWindowAdapter
{
    private View popup = null;
    private LayoutInflater inflater = null;

    public PopupAdapter(LayoutInflater inflater)
    {
        this.inflater = inflater;
    }

    @Override
    public View getInfoWindow(Marker marker)
    {
        return null;
    }

    @Override
    public View getInfoContents(Marker marker)
    {
        if (popup == null)
        {
            popup = inflater.inflate(R.layout.marker_popup, null);
        }

        TextView markerTitle = popup.findViewById(R.id.marker_title);
        TextView markerSnippet = popup.findViewById(R.id.marker_snippet);

        markerTitle.setText(marker.getTitle());
        markerSnippet.setText(marker.getSnippet());

        return popup;
    }
}
